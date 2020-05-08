package kafka.kafkaclear;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import kafka.jsonlogclearutil.logclearutil.InsertConentUtil;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ClearUtil {
    @SneakyThrows
    public static String clearRun(String value)  {
        Map<String,String> a = new HashMap<>();
        String json = value.replace(" ","");
        JSONObject jsonObject = JSON.parseObject(value);
        InsertConentUtil insertConentUtil = InsertConentUtil.getInstance();
            String stringArryValue = insertConentUtil.run(String.valueOf(jsonObject));
            a.putIfAbsent(String.valueOf(UUID.randomUUID()),stringArryValue);
           return stringArryValue;
        /*source.flatMapValues((ValueMapper<String, Iterable<?>>) value -> Collections.singleton(ClearUtil.clearRun(value)))
    */
    }
}
