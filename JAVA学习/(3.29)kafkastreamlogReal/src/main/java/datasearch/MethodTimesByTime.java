package datasearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import getdatafrommongo.GetJsons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*读取清理出的数据并找出每个method的次数*/
public class MethodTimesByTime {
    public static String methodtimes(String datebegin, String dateend) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datebegin2 = format.parse(datebegin);
        Date dateend2 = format.parse(dateend);
        /*List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();*/
        JSONObject methodsort10 = new JSONObject();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        String data = GetJsons.getJsons();
        /*String data = DataTest.getdatas();*/
        String[] jsonObjects = data.split("\n");
        for (String jsonObject : jsonObjects
        ) {
            JSONObject jsonObject1 = JSON.parseObject(jsonObject);
            String time = String.valueOf(jsonObject1.get("time"));
            Date date = format.parse(time);
            int compare1 = date.compareTo(datebegin2);
            int compare2 = date.compareTo(dateend2);
            if (compare1 < 0 || compare2 > 0) continue;
            String method = String.valueOf(jsonObject1.get("method"));
            Integer jundge = maps.putIfAbsent(method, 1);
            if (jundge != null) {
                Iterator<String> iter = maps.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Integer value = maps.get(key);
                    if (key.equals(method)) {
                        maps.put(method, ++value);
                    }
                }
            }

        }

        /*对Maps进行排序*/
        List<String> keys = MapSortUtil.sortMapByValue(maps);
        /*取出前十个存入JSON*/
        for (int i = 0; i < 10 && i < keys.size(); i++) {
            String key = keys.get(i);
            Integer value = maps.get(key);
            methodsort10.put(key, value);
        }
        String methodOut = String.valueOf(methodsort10);
        return methodOut;
    }
/*测试*/
    public static void main(String[] args) throws ParseException {
        String test = methodtimes("2020-02-28 03:05:18", "2022-02-28 05:05:18");
        System.out.println(test);
    }
}
