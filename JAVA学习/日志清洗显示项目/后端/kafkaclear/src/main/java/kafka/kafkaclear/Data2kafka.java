package kafka.kafkaclear;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.UUID;

/*将外部文件中的数据输入到kafka*/
public class Data2kafka {

    public static void dataInput(String url) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("deserializer.encoding", "UTF8");
        kafkaProps.put("key.deserializer.encoding", "UTF8");
        kafkaProps.put("value.deserializer.encoding", "UTF8");
        kafkaProps.put("max_poll_interval_ms", "600000");
        kafkaProps.put("queue.enqueue.timeout.ms", "0");
        kafkaProps.put("batch.num.messages", "1000");
        kafkaProps.put("producer.type", "async");
        kafkaProps.put("message.max.bytes", "3000000B");
        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);

        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(url)));
            BufferedReader in = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8), 10 * 1024 * 1024);//10M缓存
            long i = 0;
            while (in.ready()) {
                String line = in.readLine();
                /* kafka处理*/
                UUID uuid = UUID.randomUUID();
                String str = uuid.toString();
                String ex = String.valueOf(i++);
                String uuidStr = str.replace("-", "") + ex;
                ProducerRecord<String, String> producerRecord =
                        new ProducerRecord<String, String>("streams-source-input", uuidStr, line);
                producer.send(producerRecord);
                /*这些用来检验每条数据是否读入*/
                i++;
                System.out.println(i);
            }
            producer.flush();
            producer.close();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
