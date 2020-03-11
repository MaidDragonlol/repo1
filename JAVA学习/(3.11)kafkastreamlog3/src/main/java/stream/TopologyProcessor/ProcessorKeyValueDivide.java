package stream.TopologyProcessor;

import org.apache.kafka.streams.processor.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.List;


public class ProcessorKeyValueDivide {

    public static class MyProcessorSupplier implements ProcessorSupplier<List<String>, List<String>> {

        @Override
        public Processor<List<String>, List<String>> get() {
            return new Processor<List<String>, List<String>>() {
                /*处理器情景接口*/
                private ProcessorContext context;
                /*一个支持放置/获取/删除和范围查询的键值存储。*/
                private KeyValueStore<String, String> kvStore;

                @Override
                @SuppressWarnings("unchecked")

                /*使用给定的初始化此处理器。 该框架确保在拓扑时每个处理器调用一次*/
                public void init(final ProcessorContext context) {
                    this.context = context;
                }

                @Override
                public void process(List<String> key, List<String> value) {
                    /*这里把fromcity和tocity放在键位*/
                    List<String> keyPart = key;  //keyPart=[i]
                    List<String> valuePart = value;  //valuePart=[msg,elipstime,fromctiy,tocity]
                    String number = keyPart.get(0);
                    String fromCity = valuePart.get(2);
                    String toCity = valuePart.get(3);
                    keyPart.add(fromCity);
                    keyPart.add(toCity);
                    valuePart.set(2, null);
                    valuePart.set(3, null);
                    context.forward(keyPart, valuePart);//keyPart=[i,fromctiy,tocity]  //valuePart=[msg,elipstime]
                    context.commit();
                }


                @Override
                public void punctuate(long timestamp) {
                }

                @Override
                public void close() {
                }
            };
        }
    }


}