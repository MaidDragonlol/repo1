package kafka.kafkaclear;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;


public class KStreamClear {
    public static void runClear() {
        String input = "streams-source-input";   //输入 topic
        String output = "streams-clear-output";  //输出 topic

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"logProcessor");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, MyEventTimeExtractor.class);
        //使用Serdes类创建序列化/反序列化所需的Serde实例 Serdes类为以下类型提供默认的实现：String、Byte array、Long、Integer和Double。
        Serde<String> stringSerde = Serdes.String();

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> simpleFirstStream = builder.stream(input, Consumed.with(stringSerde, stringSerde));
        // 使用KStream.mapValues 将输入数据流以 abc: 拆分获取下标为 1 字符串
        KStream<String, String> upperCasedStream = simpleFirstStream.mapValues(ClearUtil::clearRun);
        // 把转换结果输出到另一个topic
        System.out.println("----------清理中----------");
        upperCasedStream.to(output, Produced.with(stringSerde, stringSerde));

        //创建和启动KStream
        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), properties);
        kafkaStreams.start();
    }
}
