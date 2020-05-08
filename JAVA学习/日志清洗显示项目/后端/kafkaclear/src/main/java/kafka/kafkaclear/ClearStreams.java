package kafka.kafkaclear;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import kafka.jsonlogclearutil.logclearutil.InsertConentUtil;
import lombok.SneakyThrows;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.json.JSONException;

import java.util.UUID;

/*目的：从源数据统计处格式为“时间，msg，fromCtiy，toCtity，method，OTA”的数据*/
public class ClearStreams implements Processor<String, String> {
    private ProcessorContext context;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        /*this.context.schedule(1000);*/

    }

    @SneakyThrows
    @Override
    public void process(String key, String value) {
        /*String valueLogs = value;*/   //源数据
        JSONObject jsonObject = JSON.parseObject(value);
        /*String[] jsonContents = value.split("\n");*/
        /*封装类实例化*/
        InsertConentUtil insertConentUtil = InsertConentUtil.getInstance();
       /* for (String jsonContent : jsonContents
        ) {*/
            /*调用封装类方法来清洗*/
            try {
                String stringArryValue = insertConentUtil.run(String.valueOf(jsonObject));
                context.forward(UUID.randomUUID().toString(), stringArryValue);
                System.out.println("-------------------------清理中--------------------------");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        /*}*/
    }

   /* @Override
    public void punctuate(long timestamp) {

    }*/

    @Override
    public void close() {

    }
}
