package stream.TopologyProcessor;

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

import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/*使用低级处理器API演示如何实现WordCount程序*/
public class ProcessorRouteFrequncy {

    public static class MyProcessorSupplier implements ProcessorSupplier<List<String>, List<String>> {

        @Override
        public Processor<List<String>, List<String>> get() {
            return new Processor<List<String>, List<String>>() {
                /*处理器情景接口*/
                private ProcessorContext context;
                /*一个支持放置/获取/删除和范围查询的键值存储。*/
                private KeyValueStore<String, List<Integer>> kvStore;

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
                            try (KeyValueIterator<String, List<Integer>> iter = kvStore.all()) {
                                System.out.println("----------- " + timestamp + " ----------- ");
                                while (iter.hasNext()) {
                                    KeyValue<String, List<Integer>> entry = iter.next();

                                    System.out.println("[" + entry.key + ", " + entry.value + "]");

                                    context.forward(entry.key, entry.value.toString());
                                }
                            }
                        }
                    });
                    this.kvStore = (KeyValueStore<String, List<Integer>>) context.getStateStore("Counts");
                }

                /*使用给定的键和值处理记录。*/
                @Override
                public void process(List<String> key, List<String> value) {
                    List<String> keyPart = key;  //keyPart=[i,fromctiy,tocity]
                    List<String> valuePart = value;  //valuePart=[msg,elipstime,null,null]
                    String number = keyPart.get(0);
                    keyPart.set(0, null);//keyPart=[null,fromctiy,tocity]
                    valuePart.clear();
                    String fromCity = keyPart.get(1);
                    String toCity = keyPart.get(2);
                    String route = fromCity + "-" + toCity;


                    /*KeyValueIterator：迭代器接口*/
                    /*迭代kvstore缓存，取出每个键值对与当前的数据进行比较*/
                    try (KeyValueIterator<String, List<Integer>> iter = kvStore.all()) {
                        while (iter.hasNext()) {
                            KeyValue<String, List<Integer>> entry = iter.next();
                            String getKey = entry.key;
                            List<Integer> getValue = entry.value;
                            /*存储次数*/
                            Integer unmberInt = Integer.getInteger(number);
                            Integer routeTimes = getValue.get(1);
                            getValue.clear();
                            getValue.set(0, unmberInt);
                            getValue.set(1, routeTimes);
                            List<Integer> judge = this.kvStore.putIfAbsent(getKey, getValue);
                            while (judge!=null){
                                Integer routeTimeValue = getValue.get(1);
                                routeTimeValue++;
                                getValue.set(1,routeTimeValue);
                                this.kvStore.put(route,getValue);}

                        }}

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

}