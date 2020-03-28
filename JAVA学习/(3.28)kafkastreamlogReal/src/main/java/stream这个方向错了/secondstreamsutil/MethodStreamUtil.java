package stream这个方向错了.secondstreamsutil;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.Stores;
import stream这个方向错了.TopologyProcessor.MethodTotal;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class MethodStreamUtil {
    public static void otamethod() throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-processor");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        // 将offset重置为最早，以便可以使用相同的预加载数据重新运行演示代码
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Topology builder = new Topology();
        builder.addSource("Source", "streams-clear-output");
        builder.addProcessor("Process", MethodTotal::new, "Source");
        builder.addStateStore(Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore("Counts"),
                Serdes.String(),
                Serdes.String()),
                "Process");
        builder.addSink("Sink", "streams-method-output", "Process");
        final KafkaStreams streams = new KafkaStreams(builder, props);
        final CountDownLatch latch = new CountDownLatch(1);


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
