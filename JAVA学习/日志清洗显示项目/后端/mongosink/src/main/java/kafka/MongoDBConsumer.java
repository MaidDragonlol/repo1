package kafka;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.cluster.BrokerEndPoint;
import kafka.common.ErrorMapping;
import kafka.common.TopicAndPartition;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.message.MessageAndOffset;
import org.bson.Document;

import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.*;

/*以MOngoDB作为kafka的消费者，且实现kafka到MongoDB的实时传输*/
public class MongoDBConsumer {
    /*连接到kafka并执行子方法，往MongoDB存入数据*/
    public static void kafka2Mongo() {
        long lastOffset = 0;
        MongoDBConsumer example = new MongoDBConsumer();
        long maxReads = 100;
        String topic = "streams-clear-output";
        int partition = 0;
        List<String> seeds = new ArrayList<String>();
        seeds.add("localhost");
        int port = 9092;
        boolean flg = false;
        /*以死循环的方式锁死进程，每5秒运行一次，每次从上次消费到kafka的offset开始消费*/
        while (!flg) {
            try {
                lastOffset = example.run(maxReads, topic, partition, seeds, port, lastOffset);
                System.out.println("现在消费到的offset是：" + lastOffset);
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("Oops:" + e);
                e.printStackTrace();
            }
        }

    }

    private List<String> m_replicaBrokers = new ArrayList<String>();

    public MongoDBConsumer() {
        m_replicaBrokers = new ArrayList<String>();
    }

    /*指定消费kakfa的位置，精确到offset。从上次消费到的offset开始消费到最后一个offset，将每个offset中的日志数据信息存入MongoDB*/
    public long run(long a_maxReads, String a_topic, int a_partition,
                    List<String> a_seedBrokers, int a_port, long offset) throws Exception {
        long lastOffset = 0;
        long beforeOffset = offset;
        // find the meta data about the topic and partition we are interested in
        //
        PartitionMetadata metadata = findLeader(a_seedBrokers, a_port, a_topic, a_partition);
        if (metadata == null) {
            System.out.println("Can't find metadata for Topic and Partition. Exiting");
            return lastOffset;
        }
        if (metadata.leader() == null) {
            System.out.println("Can't find Leader for Topic and Partition. Exiting");
            return lastOffset;
        }
        String leadBroker = metadata.leader().host();
        String clientName = "Client_" + a_topic + "_" + a_partition;

        SimpleConsumer consumer = new SimpleConsumer(leadBroker, a_port,
                100000, 64 * 1024, clientName);
        long readOffset = getLastOffset(consumer, a_topic, a_partition,
                kafka.api.OffsetRequest.EarliestTime(), clientName);
        int numErrors = 0;
        while (a_maxReads > 0) {
            if (consumer == null) {
                consumer = new SimpleConsumer(leadBroker, a_port,
                        100000, 64 * 1024, clientName);
            }
            FetchRequest req = new FetchRequestBuilder()
                    .clientId(clientName)
                    .addFetch(a_topic, a_partition, readOffset, 10000000)
                    .build();
            FetchResponse fetchResponse = consumer.fetch(req);

            if (fetchResponse.hasError()) {
                numErrors++;
                short code = fetchResponse.errorCode(a_topic, a_partition);
                System.out.println("Error fetching data from the Broker:"
                        + leadBroker + " Reason: " + code);
                if (numErrors > 5) break;
                if (code == ErrorMapping.OffsetOutOfRangeCode()) {
                    readOffset = getLastOffset(consumer, a_topic, a_partition,
                            kafka.api.OffsetRequest.LatestTime(), clientName);
                    continue;
                }
                consumer.close();
                consumer = null;
                leadBroker = findNewLeader(leadBroker, a_topic, a_partition, a_port);
                continue;
            }
            numErrors = 0;
            long numRead = 0;

            MongoClient client = new MongoClient();
            MongoDatabase db = client.getDatabase("clusterdb");
            MongoCollection<Document> fishCollection = db.getCollection("fish");
            Gson gson = new Gson();
            Type type = new TypeToken<Fish>() {
            }.getType();
            for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(a_topic,
                    a_partition)) {
                long currentOffset = messageAndOffset.offset();
                if (currentOffset < readOffset) {
                    System.out.println("Found an old offset: " + currentOffset
                            + " Expecting: " + readOffset);
                    continue;
                }
                readOffset = messageAndOffset.nextOffset();
                ByteBuffer payload = messageAndOffset.message().payload();

                byte[] bytes = new byte[payload.limit()];
                payload.get(bytes);
                Fish incomingFish = gson.fromJson(new String(bytes, "UTF-8"), type);
                if (beforeOffset < currentOffset) {
                    fishCollection.insertOne(incomingFish.getFishAsDocument());
                    /*控制台打印出存储信息和offset位置数据，可以更好地监控程序运行状态，当控制台重复打印即可获知总消费日志条数*/
                    System.out.println(incomingFish + String.valueOf(currentOffset));
                    lastOffset = currentOffset;
                }
                numRead++;
                a_maxReads--;
            }

            if (numRead == 0) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ie) {
                }
            }
        }
        if (consumer != null) consumer.close();
        lastOffset = (lastOffset > beforeOffset) ? lastOffset : beforeOffset;
        return lastOffset;
    }

    /*获取指定kakfa topic最后一个offset*/
    public static long getLastOffset(SimpleConsumer consumer, String topic, int partition,
                                     long whichTime, String clientName) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo =
                new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
        OffsetRequest request = new OffsetRequest(
                requestInfo, kafka.api.OffsetRequest.CurrentVersion(), clientName);
        OffsetResponse response = consumer.getOffsetsBefore(request);

        if (response.hasError()) {
            System.out.println("Error fetching data Offset Data the Broker. Reason: "
                    + response.errorCode(topic, partition));
            return 0;
        }
        long[] offsets = response.offsets(topic, partition);
        return offsets[0];
    }

    /*kafka消息队列选举出新的leader*/
    private String findNewLeader(String a_oldLeader, String a_topic, int a_partition,
                                 int a_port) throws Exception {
        for (int i = 0; i < 6; i++) {
            boolean goToSleep = false;
            PartitionMetadata metadata = findLeader(m_replicaBrokers, a_port, a_topic,
                    a_partition);
            if (metadata == null) {
                goToSleep = true;
            } else if (metadata.leader() == null) {
                goToSleep = true;
            } else if (a_oldLeader.equalsIgnoreCase(metadata.leader().host()) && i == 0) {
                // first time through if the leader hasn't changed give ZooKeeper
                // a second to recover second time, assume the broker did recover before failover,
                // or it was a non-Broker issue
                goToSleep = true;
            } else {
                return metadata.leader().host();
            }
            if (goToSleep) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                }
            }
        }
        System.out.println("Unable to find new leader after Broker failure. Exiting");
        throw new Exception("Unable to find new leader after Broker failure. Exiting");
    }
    /*找出当前消息队列选举出的leader*/
    private PartitionMetadata findLeader(List<String> a_seedBrokers, int a_port,
                                         String a_topic, int a_partition) {
        PartitionMetadata returnMetaData = null;
        loop:
        for (String seed : a_seedBrokers) {
            SimpleConsumer consumer = null;
            try {
                consumer = new SimpleConsumer(seed, a_port, 100000, 64 * 1024, "leaderLookup");
                List<String> topics = Collections.singletonList(a_topic);
                TopicMetadataRequest req = new TopicMetadataRequest(topics);
                TopicMetadataResponse resp = consumer.send(req);

                List<TopicMetadata> metaData = resp.topicsMetadata();
                for (TopicMetadata item : metaData) {
                    for (PartitionMetadata part : item.partitionsMetadata()) {
                        if (part.partitionId() == a_partition) {
                            returnMetaData = part;
                            break loop;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error communicating with Broker [" + seed + "] to find Leader for ["
                        + a_topic
                        + ", " + a_partition + "] Reason: " + e);
            } finally {
                if (consumer != null) consumer.close();
            }
        }
        if (returnMetaData != null) {
            m_replicaBrokers.clear();
            for (BrokerEndPoint replica : returnMetaData.replicas()) {
                m_replicaBrokers.add(replica.host());
            }
        }
        return returnMetaData;
    }
}