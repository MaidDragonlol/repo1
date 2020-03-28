package stream这个方向错了.TopologyProcessor;

import logclearutil.InsertConentUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.json.JSONException;

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
                stringArryValue.add("\n");
                String result = StringUtils.join(stringArryValue, ",");

               /* String result = stringArryValue.toString();*/
                this.kvStore.put(UUID.randomUUID().toString(),result);
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
