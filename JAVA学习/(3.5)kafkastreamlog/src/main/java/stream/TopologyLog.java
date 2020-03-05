package stream;

import com.alibaba.fastjson.JSONArray;
import kafkastreamlogs.JsonPaser;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.*;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.Stores;

import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
/*3.5日尝试流处理拓扑*/
/*使用低级处理器API实现程序*/
public class TopologyLog {

    private static class MyProcessorSupplier implements ProcessorSupplier<String, String> {

        @Override
        public Processor<String, String> get() {
            return new Processor<String, String>() {
                /*处理器情景接口*/
                private ProcessorContext context;
                /*一个支持放置/获取/删除和范围查询的键值存储。*/
                private KeyValueStore<String, Integer> kvStore;

                @Override
                @SuppressWarnings("unchecked")

                /*使用给定的初始化此处理器。 该框架确保在拓扑时每个处理器调用一次*/
                public void init(final ProcessorContext context) {
                    this.context = context;
                    /*安排处理器的定期操作。*/
                    this.context.schedule(1000, PunctuationType.STREAM_TIME, new Punctuator() {
                        /*如果此处理器一起执行定期操作*/
                        @Override
                        public void punctuate(long timestamp) {
                            /*KeyValueIterator：迭代器接口*/
                            try (KeyValueIterator<String, Integer> iter = kvStore.all()) {
                                System.out.println(java.util.UUID.randomUUID().toString());

                                while (iter.hasNext()) {
                                    KeyValue<String, Integer> entry = iter.next();

                                    System.out.println("[" + entry.key + ", " + entry.value + "]");

                                    context.forward(entry.key, entry.value.toString());
                                }
                            }
                        }
                    });
                    this.kvStore = (KeyValueStore<String, Integer>) context.getStateStore("Counts");
                }

                /*使用给定的键和值处理记录。*/
                @Override
                public void process(String dummy, String line) {
                    String jsonContent = JSONArray.toJSONString(line);

                    /*目的是输入value抽取结果并输出*/
                    try {
                        JsonPaser.insertConent(jsonContent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    context.commit();
                }

                @Override
                @Deprecated
                public void punctuate(long timestamp) {
                }

                @Override
                public void close() {
                }
            };
        }
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-processor");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // 将offset重置为最早，以便可以使用相同的预加载数据重新运行演示代码
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Topology builder = new Topology();

        builder.addSource("Source", "streams-input");

        builder.addProcessor("Process", new MyProcessorSupplier(), "Source");
        builder.addStateStore(Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore("Counts"),
                Serdes.String(),
                Serdes.Integer()),
                "Process");

        builder.addSink("Sink", "streams-processor-output", "Process");

        final KafkaStreams streams = new KafkaStreams(builder, props);
        final CountDownLatch latch = new CountDownLatch(1);

        /*注册一个新的虚拟机关闭挂钩。*/
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }
}