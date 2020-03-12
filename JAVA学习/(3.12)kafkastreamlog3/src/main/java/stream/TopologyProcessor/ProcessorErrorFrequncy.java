package stream.TopologyProcessor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.*;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import java.util.*;

public class ProcessorErrorFrequncy {

    public static class MyProcessorSupplier implements ProcessorSupplier<List<String>, List<String>> {

        @Override
        public Processor<List<String>, List<String>> get() {
            return new Processor<List<String>, List<String>>() {
                private ProcessorContext context;
                private KeyValueStore<String, List<Integer>> kvStore;
                @Override
                @SuppressWarnings("unchecked")
                public void init(final ProcessorContext context) {
                    this.context = context;
                    this.context.schedule(1000, PunctuationType.STREAM_TIME, new Punctuator() {
                        /*punctuate指定为每1000ms从kvstore里面传递到下一个topic一次*/
                        @Override
                        public void punctuate(long timestamp) {
                            try (KeyValueIterator<String, List<Integer>> iter = kvStore.all()) {
                                while (iter.hasNext()) {
                                    KeyValue<String, List<Integer>> entry = iter.next();
                                    context.forward(entry.key, entry.value.toString());
                                }
                            }
                        }
                    });
                    this.kvStore = (KeyValueStore<String, List<Integer>>) context.getStateStore("Counts");
                }

                @Override
                public void process(List<String> key, List<String> value) {
                    List<Integer> kvStoreValue = new ArrayList<>(); /*kvStoreValue这个数组用来存储每条航线次数和错误次数：[这条航线总次数,这条航线错误次数]*/
                    List<String> keyPart = key;  //keyPart=[number,route]
                    List<String> valuePart = value;  //valuePart=[msg,elipstime]
                    String route = keyPart.get(1);
                    keyPart.clear();
                    keyPart.set(0,route);  //keyPart=[route]
                    String msg = valuePart.get(0);
                    valuePart.clear();
                    valuePart.set(0,msg);   //valuePart=[msg]
                    /*判断msg成功与否，成功errorTimes = 0;失败errorTimes = 1;*/
                    String msgOfIt = valuePart.get(0);
                    Integer errorTimes;
                    if (msgOfIt.equals("success")) {
                        errorTimes = 0;
                    } else {
                        errorTimes = 1;
                    }
                    kvStoreValue.add(1);
                    kvStoreValue.add(errorTimes);  //kvStoreValue=[1,1]或者[1,0]
                    /*如果没有这条航线就提前插入。说明：putIfAbsent方法执行失败，即找到旧route，就会返回就route的值，没找到返回空*/
                    List<Integer> judge = kvStore.putIfAbsent(route, kvStoreValue);
                    if (judge != null) {
                        /*迭代kvstore缓存，取出每个键值对与当前的数据进行比较*/
                        try (KeyValueIterator<String, List<Integer>> iter = kvStore.all()) {
                            while (iter.hasNext()) {
                                KeyValue<String, List<Integer>> entry = iter.next();
                                String getKey = entry.key;
                                List<Integer> getValue = entry.value;
                                if (getKey.equals(route)) {
                                    Integer totalTimes = getValue.get(0);
                                    getValue.set(0,++totalTimes);
                                    Integer totalErroTimes = getValue.get(1);
                                    totalErroTimes+=errorTimes;
                                    getValue.set(1,totalErroTimes);  //getValue=[2,1]即[总次数+1，错误次数+1或+0]
                                    /*这里不知道对不对，我的目的是对getkey这个键值对进行更新*/
                                    kvStore.put(getKey, getValue);//返回格式：[route,[航线次数,错误次数]]
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