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

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/*使用低级处理器API演示如何实现WordCount程序*/
public class ProcessorErrorFrequncy {

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
                    String msg = valuePart.get(0);
                    valuePart.clear();
                    valuePart.set(0,msg);//valuePart=[msg]
                    valuePart.clear();
                    String fromCity = keyPart.get(1);
                    String toCity = keyPart.get(2);
                    String route = fromCity + "-" + toCity;
                    keyPart.clear();
                    keyPart.set(0,route);//keyPart=[route]
                    /*KeyValueIterator：迭代器接口*/
                    List<Integer> kvStoreValue = new ArrayList<>();
                    kvStoreValue.add(1);
                    String msgOfIt = valuePart.get(0);

                    Integer errorTimes;
                    if (msgOfIt.equals("success")){
                        errorTimes = 0;
                    }else{errorTimes = 1;}
                    /*kvStoreValue这个数组用来存储每条航线次数和错误次数：[航线次数,错误次数]*/
                    kvStoreValue.add(1);
                    kvStoreValue.add(errorTimes);
                    /*如果没有这条航线就提前插入。说明：putIfAbsent方法执行失败，即找到旧route，就会返回就route的值*/
                    List<Integer> judge = kvStore.putIfAbsent(route,kvStoreValue);
                    if(judge==null){
                        /*迭代kvstore缓存，取出每个键值对与当前的数据进行比较*/
                        try (KeyValueIterator<String, List<Integer>> iter = kvStore.all()) {

                            while (iter.hasNext()) {
                                KeyValue<String, List<Integer>> entry = iter.next();
                                String getKey = entry.key;
                                List<Integer> getValue = entry.value;
                                if (getKey.equals(route)) {
                                Integer totalTimes = getValue.get(0);
                                totalTimes++;
                                Integer totalErroTimes = getValue.get(1);
                                totalErroTimes++;
                                getValue.clear();
                                getValue.add(0,totalTimes);
                                getValue.add(1,totalErroTimes);
                                /*这里不知道对不对，我的目的是对getkey这个键值对进行更新*/
                                kvStore.put(getKey,getValue);//返回格式：[route,[航线次数,错误次数]]
                                break;

                                }
                            }
                        }
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
}