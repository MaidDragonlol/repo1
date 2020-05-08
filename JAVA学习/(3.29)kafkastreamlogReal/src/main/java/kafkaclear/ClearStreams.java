package kafkaclear;

import logclearutil.InsertConentUtil;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

/*目的：从源数据统计处格式为“时间，msg，fromCtiy，toCtity，method，OTA”的数据*/
public class ClearStreams implements Processor<String, String> {
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
                /*这里把fromCity和toCity合成route:"SSA-MOX"*/
                String time = stringArryValue.get(0);
                String msg = stringArryValue.get(1);
                String fromCity = stringArryValue.get(2);
                String toCity = stringArryValue.get(3);
                String route = fromCity + "-" + toCity;
                String method = stringArryValue.get(4);
                String ota = stringArryValue.get(5);
                stringArryValue.clear();
                JSONObject obj = new JSONObject();
                obj.put("_id", UUID.randomUUID().toString());
                obj.put("time", time);
                obj.put("msg", msg);
                obj.put("route", route);
                obj.put("method", method);
                obj.put("ota", ota);
                String result2 = String.valueOf(obj);
                this.kvStore.put(UUID.randomUUID().toString(), result2);
            } catch (JSONException e) {
                e.printStackTrace();
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
