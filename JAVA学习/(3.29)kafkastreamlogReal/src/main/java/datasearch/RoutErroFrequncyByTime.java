package datasearch;

import aplicationmain.DataTest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import getdatafrommongo.GetJsons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/*统计航线的错误率：取次数排行前十的航线统计错误率*/
public class RoutErroFrequncyByTime {
    public static String routerrofrequncy(String datebegin, String dateend) throws ParseException {
        Integer errorTime = 1;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datebegin2 = format.parse(datebegin);
        Date dateend2 = format.parse(dateend);
        /*List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();*/
        JSONObject routesort10 = new JSONObject();
        Map<String, Integer> maps = new HashMap<String, Integer>();//航线次数
        Map<String, Integer> maps2 = new HashMap<String, Integer>();//错误次数

        String data = GetJsons.getJsons();
       /* String data = DataTest.getdatas();*/
        String[] jsonObjects = data.split("\n");
        for (String jsonObject : jsonObjects
        ) {
            JSONObject jsonObject1 = JSON.parseObject(jsonObject);
            String time = String.valueOf(jsonObject1.get("time"));
            Date date = format.parse(time);
            int compare1 = date.compareTo(datebegin2);
            int compare2 = date.compareTo(dateend2);
            if (compare1 < 0 || compare2 > 0) continue;
            String route = String.valueOf(jsonObject1.get("route"));
            String msg = String.valueOf(jsonObject1.get("msg"));
            if(msg.equals("success")){
                errorTime = 0;
            }
            /*每个航线次数*/
            Integer jundge = maps.putIfAbsent(route, 1);
            if (jundge != null) {
                Iterator<String> iter = maps.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Integer value = maps.get(key);
                    if (key.equals(route)) {
                        maps.put(route, ++value);
                    }
                }
            }
            /*每个航线错误次数*/
            Integer jundge2 = maps2.putIfAbsent(route, errorTime);
            if (jundge2 != null) {
                Iterator<String> iter = maps2.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Integer errovalue = maps.get(key);
                    if (key.equals(route)) {
                        maps.put(route, errovalue+errorTime);
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
            Integer erroTimes = maps2.get(key);
            Double errorFrequncy = Double.valueOf(erroTimes)/Double.valueOf(value);
            routesort10.put(key, errorFrequncy);
        }
        String methodOut = String.valueOf(routesort10);
        return methodOut;
    }
    /*测试*/
    public static void main(String[] args) throws ParseException {
        String test = routerrofrequncy("2020-02-28 03:05:18", "2022-02-28 05:05:18");
        System.out.println(test);
    }

}

