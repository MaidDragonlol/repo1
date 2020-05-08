package springboot.springboot.kafkaclear.getdatafrommongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.time.DateUtils;
import org.bson.Document;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;

public class DataSearch {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("clusterdb");
    MongoCollection<Document> collection = database.getCollection("fish");

    /*对map进行排序*/
    public List<String> sortMapByValue(Map<String, Integer> map) {
        int size = map.size();
        //通过map.entrySet()将map转换为"1.B.1.e=78"形式的list集合
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(size);
        list.addAll(map.entrySet());
        List<String> keys = list.stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                .map(Map.Entry<String, Integer>::getKey)
                .collect(Collectors.toList());
        return keys;
    }

    /* 时间格式转换，例如：2018-06-16 10:00:00--->Sun Feb 28 04:10:33 CST 2021*/
    private static Date stringToDate(String date) throws ParseException {
        Date date2 = DateUtils.parseDate(date, "yyyy-MM-dd HH:mm:ss");
        return date2;
    }

    /* 时间格式转换，例如：Sun Feb 28 04:10:33 CST 2021--->2018-06-16 10:00:00*/
    private static String dateToString(Date date) throws ParseException {
        SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        String format2 = sfEnd.format(sfStart.parse(String.valueOf(date)));
        return format2;
    }

    /*数据库查找*/
    private long countElement(String startTime, String endTime, String key, String value) {
        long count = collection.count(and(gt("time", startTime), lt("time", endTime), eq(key, value)));
        return count;
    }

    /*开始时间后两小时，每十分钟日志量统计*/
    public String logCountStartByTime(String startTime) throws ParseException {
        Date date = stringToDate(startTime);
        JSONArray jsonArray = new JSONArray();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < 12; i++) {
            JSONObject logTotalTime = new JSONObject();
            String startDate = dateToString(calendar.getTime());
            calendar.add(Calendar.MINUTE, 10);
            String endTDate = dateToString(calendar.getTime());
            long doc = collection.count(and(gt("time", startDate), lt("time", endTDate)));
            logTotalTime.put(startDate, doc);
            jsonArray.add(logTotalTime);
        }
        String result = jsonArray.toString();
        return result;
    }

    /*日志中出现的4种接口统计*/
    public String MethodTimesByTime(String startTime, String endTime) throws ParseException {
        JSONArray jsonArray = new JSONArray();
        JSONObject methodcount1 = new JSONObject();
        JSONObject methodcount2 = new JSONObject();
        JSONObject methodcount3 = new JSONObject();
        JSONObject methodcount4 = new JSONObject();
        /*TuniuController.query(..)*/
        long count1 = countElement(startTime, endTime, "method", "TuniuController.query(..)");
        methodcount1.put("TuniuController.query(..)", count1);
        jsonArray.add(methodcount1);
        /*JintongController.recommend(..)*/
        long count2 = countElement(startTime, endTime, "method", "JintongController.recommend(..)");
        methodcount2.put("JintongController.recommend(..)", count2);
        jsonArray.add(methodcount2);
        /*SelfController.recommend(..)*/
        long count3 = countElement(startTime, endTime, "method", "SelfController.recommend(..)");
        methodcount3.put("SelfController.recommend(..)", count3);
        jsonArray.add(methodcount3);
        /*TrafreeController.recommend(..)*/
        long count4 = countElement(startTime, endTime, "method", "TrafreeController.recommend(..)");
        methodcount4.put("TrafreeController.recommend(..)", count4);
        jsonArray.add(methodcount4);
        String methodOut = String.valueOf(jsonArray);
        return methodOut;
    }


    /*日志中出现的4种OTA统计*/
    public String OtaTimesByTime(String startTime, String endTime) throws ParseException {
        JSONArray jsonArray = new JSONArray();
        JSONObject methodcount1 = new JSONObject();
        JSONObject methodcount2 = new JSONObject();
        JSONObject methodcount3 = new JSONObject();
        JSONObject methodcount4 = new JSONObject();
        long count1 = countElement(startTime, endTime, "ota", "JINTONG");
        methodcount1.put("JINTONG", count1);
        jsonArray.add(methodcount1);
        long count2 = countElement(startTime, endTime, "ota", "TUNIU");
        methodcount2.put("TUNIU", count2);
        jsonArray.add(methodcount2);
        long count3 = countElement(startTime, endTime, "ota", "TRAFREE");
        methodcount3.put("TRAFREE", count3);
        jsonArray.add(methodcount3);
        long count4 = countElement(startTime, endTime, "ota", "8000YI");
        methodcount4.put("8000YI", count4);
        jsonArray.add(methodcount4);
        String methodOut = String.valueOf(jsonArray);
        return methodOut;
    }

    /*统计一个时间段里前十出现次数的航线*/
    public String RouteTimesByTime(String startTime, String endTime) throws ParseException {
        JSONArray jsonArray = new JSONArray();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        FindIterable<Document> doc = collection.find(and(gt("time", startTime), lt("time", endTime)));
        MongoCursor<Document> datas = doc.iterator();
        while (datas.hasNext()) {
            String route = String.valueOf(datas.next().get("route"));
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
        }
        /*对Maps进行排序*/
        List<String> keys = sortMapByValue(maps);
        /*取出前十个存入JSON*/
        for (int i = 0; i < 10 && i < keys.size(); i++) {
            JSONObject routesort = new JSONObject();
            String key = keys.get(i);
            Integer value = maps.get(key);
            routesort.put(key, value);
            jsonArray.add(routesort);
        }
        String routeOut = String.valueOf(jsonArray);
        return routeOut;
    }

    /*每条航线的错误率*/
    public String RouteErroFrequncyByTime(String startTime, String endTime) throws ParseException {
        JSONArray jsonArray = new JSONArray();
        Gson gson = new Gson();
        Map<String, Integer> maps2 = new HashMap();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        FindIterable<Document> doc = collection.find(and(gt("time", startTime), lt("time", endTime)));
        MongoCursor<Document> datas = doc.iterator();
        while (datas.hasNext()) {
            Integer errorTime = 1;
            JSONObject oneJson = new JSONObject();
            String json = gson.toJson(datas.next());
            oneJson = JSON.parseObject(json);
            String route = String.valueOf(oneJson.get("route"));
            String msg = String.valueOf(oneJson.get("msg"));
            if (msg.equals("success")) {
                errorTime = 0;
            }
            Integer jundge = maps.putIfAbsent(route, 1);
            maps2.putIfAbsent(route, errorTime);
            if (jundge != null) {
                Iterator<String> iter = maps.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Integer value = maps.get(key);
                    Integer errovalue = maps.get(key);
                    if (key.equals(route)) {
                        maps.put(route, ++value);
                        maps2.put(route, errovalue + errorTime);
                    }
                }
            }
        }
        /*对Maps进行排序*/
        List<String> keys = sortMapByValue(maps);
        /*取出前十个存入JSON*/
        for (int i = 0; i < 10 && i < keys.size(); i++) {
            JSONObject routesort = new JSONObject();
            String key = keys.get(i);
            Integer value = maps.get(key);
            Integer erroTimes = maps2.get(key);
            Double errorFrequncy = Double.valueOf(erroTimes) / Double.valueOf(value);
            /*保留2位有效数字*/
            BigDecimal bigDecimal = new BigDecimal(errorFrequncy);
            bigDecimal = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
            routesort.put(key, bigDecimal);
            jsonArray.add(routesort);
        }
        String routeOut = String.valueOf(jsonArray);
        return routeOut;
    }

    /*一段时间内航线的错误次数前十*/
    public String RouteErrorTimesByTime(String startTime, String endTime) throws ParseException {
        JSONArray jsonArray = new JSONArray();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        FindIterable<Document> doc = collection.find(and(gt("time", startTime), lt("time", endTime)));
        MongoCursor<Document> datas = doc.iterator();
        while (datas.hasNext()) {
            Integer errorTime = 1;
            String msg = String.valueOf(datas.next().get("msg"));
           /* String msg = String.valueOf(datas.next().get("msg"));
            if (msg.equals("success")) {
                errorTime = 0;
            }*/
            Integer jundge = maps.putIfAbsent(msg, errorTime);
            if (jundge != null) {
                Iterator<String> iter = maps.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Integer value = maps.get(key);
                    if (key.equals(msg)) {
                        maps.put(msg, value + errorTime);
                    }
                }
            }
        }
        /*对Maps进行排序*/
        List<String> keys = sortMapByValue(maps);
        /*取出前十个存入JSON*/
        for (int i = 0; i < 10 && i < keys.size(); i++) {
            JSONObject msgsort = new JSONObject();
            String key = keys.get(i);
            Integer value = maps.get(key);
            msgsort.put(key, value);
            jsonArray.add(msgsort);
        }
        String msgOut = String.valueOf(jsonArray);
        return msgOut;
    }


}




