package searchmethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import getdatafrommongo.GetJsons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/*读取清理出的数据并找出每个method的次数*/
public class methodtimesbytime {
    public static String methodtimes(Date datebegin, Date dateend) throws ParseException {
        /*List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();*/
        JSONObject method10 = new JSONObject();
        Map<String, String> mapdown = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                });
        Map<String, Integer> maps = new HashMap<String, Integer>();
        String data = GetJsons.getJsons();
        String[] jsonObjects = data.split("\n");
        for (String jsonObject : jsonObjects
        ) {
            JSONObject jsonObject1 = JSON.parseObject(jsonObject);
            String time = String.valueOf(jsonObject1.get("time"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(time);
            int compare1 = date.compareTo(datebegin);
            int compare2 = date.compareTo(dateend);
            if (compare1 < 0 && compare2 > 0) continue;
            String method = String.valueOf(jsonObject1.get("method"));
            Iterator<String> iter = maps.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                Integer value = maps.get(key);
                if (key.equals(method)) {
                    maps.put(method, ++value);
                } else {
                    maps.put(method, 1);
                }
            }
        }
        Iterator<Map.Entry<String, Integer>> iter = maps.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, Integer> entry = iter.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            mapdown.put(value.toString(),key);
        }
        for(Map.Entry<String, String> vo : mapdown.entrySet()){

    }
}
