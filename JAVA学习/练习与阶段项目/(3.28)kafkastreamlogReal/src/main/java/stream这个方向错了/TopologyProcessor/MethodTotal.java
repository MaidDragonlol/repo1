package stream这个方向错了.TopologyProcessor;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.ArrayList;
import java.util.List;
/*目的：从格式为“时间，msg，fromCtiy，toCtity，method，OTA”统计出数据格式为“时间，method次数”*/
public class MethodTotal implements Processor<String, String> {
    private ProcessorContext context;
    private KeyValueStore<String, String> kvStore;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        this.context.schedule(1000);
        this.kvStore = (KeyValueStore<String, String>) context.getStateStore("Counts");
    }

    @Override
    public void process(String key, String value) {
        String keyPart = key;
        String valueLogs = value;   //源数据
        List<String> store = new ArrayList<>();
        List<String> newOne = new ArrayList<>();
        String[] valueLog = valueLogs.split(",");
        for (String data : valueLog
        ) {
            store.add(data);
            if (data.equals("\n")) {
                String time = store.get(0);
                String method = store.get(5);
                newOne.add(time);
                newOne.add(method);
                newOne.add("1");
                String newOneString = StringUtils.join(newOne, ",");
                /*如果原来的航线存在则存入新航线且次数为一*/
                String judge = this.kvStore.putIfAbsent(method, newOneString);
                newOne.clear();
                /*如果原来的航线存在则次数自加一*/
                if (judge != null) {
                    try (KeyValueIterator<String, String> iter = kvStore.all()) {
                        while (iter.hasNext()) {
                            KeyValue<String, String> entry = iter.next();
                            String getKey = entry.key;
                            String getValue = entry.value;
                            if (getKey.equals(method)) {
                                String[] kvStorValuePart = getValue.split(",");
                                /*总次数加一*/
                                Integer timesNow = Integer.valueOf(kvStorValuePart[2]);
                                kvStorValuePart[2]=String.valueOf(timesNow++);
                                kvStorValuePart[3]="\n";
                                String addOneString = StringUtils.join(kvStorValuePart, ",");
                                this.kvStore.put(method, addOneString);
                                break;
                            }
                        }
                    }
                }
                store.clear();
            }
        }
    }

    @Override
    public void punctuate(long timestamp) {
        try (KeyValueIterator<String, String> iterator = this.kvStore.all()) {
            iterator.forEachRemaining(entry -> {
                context.forward(entry.key, entry.value);
                this.kvStore.delete(entry.key);
            });
        }
        context.commit();
    }

    @Override
    public void close() {
        this.kvStore.close();
    }
}
