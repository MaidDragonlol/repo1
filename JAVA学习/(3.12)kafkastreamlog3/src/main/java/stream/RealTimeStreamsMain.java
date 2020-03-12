package stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.Stores;
import stream.TopologyProcessor.ProcessorErrorFrequncy;
import stream.TopologyProcessor.ProcessorKeyValueDivide;
import stream.TopologyProcessor.ProcessorSourceClear;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class RealTimeStreamsMain {
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
        /*从produncer接收源JSON数据并清洗*/
        builder.addSource("Source0", "streams-source-input");
        builder.addProcessor("Process0", new ProcessorSourceClear.MyProcessorSupplier(), "Source0");
        builder.addStateStore(Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore("Counts0"),
                Serdes.String(),
                Serdes.Integer()),
                "Process0");
        builder.addSink("Sink0", "streams-clear-output", "Process0");
        /*-----------------------------------------------------------------------------------------------------------------------*/
        /*数组键值对输入并重新组合*/
        builder.addSource("Source1", "streams-clear-input");  //前面KafkaProducerLog向streams-input这个topic发送数据：<1(第一条),[search_lack_of_data_error, 4751, TPA, BUF]>
        builder.addProcessor("Process1", new ProcessorKeyValueDivide.MyProcessorSupplier(), "Source1");
        builder.addStateStore(Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore("Counts1"),
                Serdes.String(),
                Serdes.Integer()),
                "Process1");
        builder.addSink("Sink", "streams-id-output", "Process1");  //流处理后输出<[1,, TPA, BUF],[search_lack_of_data_error, 4751]>
        /*-----------------------------------------------------------------------------------------------------------------------*/
        /*每台航线出现的错误率*/
        builder.addSource("Source2", "streams-id-output");//topic数据：<1,[search_lack_of_data_error, 4751, TPA, BUF]>
        builder.addProcessor("Process2", new ProcessorErrorFrequncy.MyProcessorSupplier(), "Process1");
        builder.addStateStore(Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore("Counts3"),
                Serdes.String(),
                Serdes.Integer()),
                "Process2");
        builder.addSink("Sink2", "streams-error-frequency-output", "Process2");//<"TPA-BUF",[总次数,错误次数]>    streams-error-frequency-output接收：<"TPA-BUF",[1,1]>


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
