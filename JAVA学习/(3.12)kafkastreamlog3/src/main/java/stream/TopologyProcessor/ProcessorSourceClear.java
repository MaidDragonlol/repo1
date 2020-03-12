package stream.TopologyProcessor;

import com.alibaba.fastjson.JSON;
import logclearutil.InsertConentUtil;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.*;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessorSourceClear {

    public static class MyProcessorSupplier implements ProcessorSupplier<String, Collections> {

        @Override
        public Processor<String, Collections> get() {
            return new Processor<String, Collections>() {
                private ProcessorContext context;
                private KeyValueStore<Integer, List<String>> kvStore;
                @Override
                @SuppressWarnings("unchecked")
                public void init(final ProcessorContext context) {
                    this.context = context;
                    this.context.schedule(1000, PunctuationType.STREAM_TIME, new Punctuator() {
                        /*punctuate指定为每1000ms从kvstore里面传递到下一个topic一次*/
                        @Override
                        public void punctuate(long timestamp) {
                            try (KeyValueIterator<Integer, List<String>> iter = kvStore.all()) {
                                while (iter.hasNext()) {
                                    KeyValue<Integer, List<String>> entry = iter.next();
                                    context.forward(entry.key, entry.value.toString());
                                }
                            }
                        }
                    });
                    this.kvStore = (KeyValueStore<Integer, List<String>>) context.getStateStore("Counts0");
                }

                @Override
                public void process(String key, Collections value) {
                    String valueString = JSON.toJSONString(value);   //源数据
                    /*分割、遍历，并且存储格式为<编号，[msg,elipstime,fromctiy,tocity]>*/
                    String[] jsonContents = valueString.split("\n");
                    int i = 1;
                    for (String jsonContent : jsonContents
                    ) {
                        /*封装类实例化*/
                        InsertConentUtil insertConentUtil = InsertConentUtil.getInstance();
                        /*调用封装类方法来清洗*/
                        try {
                            List<String> stringArryValue = insertConentUtil.run(jsonContent);
                            List<String> stringArryKey = new ArrayList<>();
                            stringArryKey.add(Integer.toString(i++));
                            this.kvStore.put(i++,stringArryValue);
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