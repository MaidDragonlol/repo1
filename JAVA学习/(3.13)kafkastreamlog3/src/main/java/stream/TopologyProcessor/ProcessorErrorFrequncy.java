package stream.TopologyProcessor;

import logclearutil.InsertConentUtil;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.*;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.json.JSONException;

import java.util.List;

public class ProcessorErrorFrequncy {

    public static class MyProcessorSupplier implements ProcessorSupplier<String, String> {

        @Override
        public Processor<String, String> get() {
            return new Processor<String, String>() {
                private ProcessorContext context;
                private KeyValueStore<String, String> kvStore;

                @Override
                @SuppressWarnings("unchecked")
                public void init(final ProcessorContext context) {
                    this.context = context;
                    this.context.schedule(1000, PunctuationType.STREAM_TIME, new Punctuator() {
                        /*punctuate指定为每1000ms从kvstore里面传递到下一个topic一次*/
                        @Override
                        public void punctuate(long timestamp) {
                            try (KeyValueIterator<String, String> iter = kvStore.all()) {
                                while (iter.hasNext()) {
                                    KeyValue<String, String> entry = iter.next();
                                    String getValue = entry.value;
                                    String[] kvStorValuePart = getValue.split("-");
                                    Integer routeTotalTimes = Integer.getInteger(kvStorValuePart[0]);
                                    Integer routeErrorTimes = Integer.getInteger(kvStorValuePart[1]);
                                    Double erroFrequncy = Double.valueOf(routeErrorTimes) / Double.valueOf(routeTotalTimes);
                                    context.forward(entry.key, erroFrequncy);
                                }
                            }
                        }
                    });
                    this.kvStore = (KeyValueStore<String, String>) context.getStateStore("Counts");
                }

                /*假定value获取了源数据*/
                @Override
                public void process(String key, String value) {
                    Integer erroTimes = 1;
                    String valueLogs = value;   //源数据
                    /*分割、遍历，并且存储格式为<编号，[msg,fromctiy,tocity]>*/
                    String[] jsonContents = valueLogs.split("\n");
                    for (String jsonContent : jsonContents
                    ) {
                        /*封装类实例化*/
                        InsertConentUtil insertConentUtil = InsertConentUtil.getInstance();
                        /*调用封装类方法来清洗*/
                        try {
                            List<String> stringArryValue = insertConentUtil.run(jsonContent);
                            String msg = stringArryValue.get(0);
                            String fromCity = stringArryValue.get(1);
                            String toCity = stringArryValue.get(2);
                            String route = fromCity + "-" + toCity;
                            if (msg.equals("success")) {
                                erroTimes = 0;
                            }
                            String judge = kvStore.putIfAbsent(route, "1" + "-" + erroTimes.toString());
                            if (judge != null) {
                                /*迭代kvstore缓存，取出每个键值对与当前的数据进行比较*/
                                try (KeyValueIterator<String, String> iter = kvStore.all()) {
                                    while (iter.hasNext()) {
                                        KeyValue<String, String> entry = iter.next();
                                        String getKey = entry.key;
                                        String getValue = entry.value;
                                        if (getKey.equals(route)) {
                                            String[] kvStorValuePart = getValue.split("-");
                                            Integer routeTotalTimes = Integer.parseInt(kvStorValuePart[0]);
                                            Integer routeErrorTimes = Integer.parseInt(kvStorValuePart[1]);
                                            String valuePartString = (++routeTotalTimes).toString() + "-" + (++routeErrorTimes).toString();
                                            this.kvStore.put(route, valuePartString);//返回格式：<route,航线次数-错误次数>
                                            break;

                                        }

                                    }
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
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