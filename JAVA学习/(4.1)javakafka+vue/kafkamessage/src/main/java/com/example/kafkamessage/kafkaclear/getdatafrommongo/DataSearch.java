package com.example.kafkamessage.kafkaclear.getdatafrommongo;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.time.DateUtils;
import org.bson.Document;
import org.junit.Test;

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

    /* 2018-06-16 10:00:00--->Sun Feb 28 04:10:33 CST 2021*/
    private static Date stringToDate(String date) throws ParseException {
        Date date2 = DateUtils.parseDate(date, "yyyy-MM-dd HH:mm:ss");
        return date2;
    }

    /* Sun Feb 28 04:10:33 CST 2021--->2018-06-16 10:00:00*/
    private static String dateToString(Date date) throws ParseException {
        SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.ENGLISH);
        String format2 = sfEnd.format(sfStart.parse(String.valueOf(date)));
        return format2;
    }

    /*数据库查找*/
    private long countElement(String startTime, String endTime, String key, String value) {
        long count = collection.count(and(gt("time", startTime), lt("time", endTime), eq(key, value)));
        return count;
    }

    /*每段时间统计*/
    public String logCountStartByTime(String startTime) throws ParseException {
        Date date = stringToDate(startTime);
        JSONObject logTotal = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < 12; i++) {
            Integer count = 0;
            String startDate = dateToString(calendar.getTime());
            calendar.add(Calendar.MINUTE, 10);
            String endTDate = dateToString(calendar.getTime());
            long doc = collection.count(and(gt("time", startDate), lt("time", endTDate)));
            logTotal.put(startDate, doc);
        }
        String result = logTotal.toString();
        return result;
    }

    /*每种接口统计*/
    public String MethodTimesByTime(String startTime, String endTime) throws ParseException {
        JSONObject methodcount = new JSONObject();
        /*TuniuController.query(..)*/
        long count1 = countElement(startTime, endTime, "method", "TuniuController.query(..)");
        methodcount.put("TuniuController.query(..)", count1);
        /*JintongController.recommend(..)*/
        long count2 = countElement(startTime, endTime, "method", "JintongController.recommend(..)");
        methodcount.put("JintongController.recommend(..)", count2);
        /*SelfController.recommend(..)*/
        long count3 = countElement(startTime, endTime, "method", "SelfController.recommend(..)");
        methodcount.put("SelfController.recommend(..)", count3);
        /*TrafreeController.recommend(..)*/
        long count4 = countElement(startTime, endTime, "method", "TrafreeController.recommend(..)");
        methodcount.put("TrafreeController.recommend(..)", count4);

        String methodOut = String.valueOf(methodcount);
        return methodOut;
    }

    /*每种OTA统计*/
    public String OtaTimesByTime(String startTime, String endTime) throws ParseException {
        JSONObject methodcount = new JSONObject();
        long count1 = countElement(startTime, endTime, "ota", "JINTONG");
        methodcount.put("TuniuController.query(..)", count1);
        long count2 = countElement(startTime, endTime, "ota", "TUNIU");
        methodcount.put("JintongController.recommend(..)", count2);
        long count3 = countElement(startTime, endTime, "ota", "8000YI");
        methodcount.put("SelfController.recommend(..)", count3);
        String methodOut = String.valueOf(methodcount);
        return methodOut;
    }

    /*统计一个时间段里前十出现次数的航线*/
    public String RouteTimesByTime(String startTime, String endTime) throws ParseException {
        JSONObject routesort10 = new JSONObject();
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
            String key = keys.get(i);
            Integer value = maps.get(key);
            routesort10.put(key, value);
        }
        String routeOut = String.valueOf(routesort10);
        return routeOut;
    }

    /*每条航线的错误率*/
    public String RouteErroFrequncyByTime(String startTime, String endTime) throws ParseException {
        Integer errorTime = 1;
        JSONObject routesort10 = new JSONObject();
        Map<String, Integer> maps2 = new HashMap();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        FindIterable<Document> doc = collection.find(and(gt("time", startTime), lt("time", endTime)));
        MongoCursor<Document> datas = doc.iterator();
        while (datas.hasNext()) {
            String route = String.valueOf(datas.next().get("route"));
            String msg = String.valueOf(datas.next().get("msg"));
            if (msg.equals("success")) {
                errorTime = 0;
            }
            Integer jundge = maps.putIfAbsent(route, 1);
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
            String key = keys.get(i);
            Integer value = maps.get(key);
            Integer erroTimes = maps2.get(key);
            Double errorFrequncy = Double.valueOf(erroTimes) / Double.valueOf(value);
            routesort10.put(key, errorFrequncy);
        }
        String routeOut = String.valueOf(routesort10);
        return routeOut+maps.toString()+maps2.toString();
    }

    /*-----------------------------------------保留方法。留待备用----------------------------------------*/

    /**
     * 存入一条数据【插入一个文档对象】
     */
    @Test
    public void SaveOne() {
        /**
         * 数据（含文档套文档） :
         {
         "name" : "MongoDB",
         "type" : "nosqlDB",
         "count" : 1,
         "versions": [ "v3.6", "v3.0", "v2.6" ],
         "info" : { x : 203, y : 102 }
         }
         */
        Map<String, Object> map = new HashMap<>();
        map.put("x", 203);
        map.put("y", 102);
        Document document = new Document("name", "MongoDB")
                .append("type", "nosqlDB")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.6", "v3.0", "v2.6"))
                .append("info", new Document(map));

        /**
         * 有了文档对象后，就是向集合collection中保存了
         * 也可以批量插入List<Document>，方法为collection.insertMany(documents);
         */

        collection.insertOne(document);

        /**
         * 要计算集合中的文档数，可以使用集合的count()方法。
         */

        System.err.println("count of documents from collection is :" + collection.count());

    }

    /**
     * 无条件取出（查询）第一个文档对象（第一条数据）
     */
    @Test
    public void findOne() {

        Document doc = collection.find().first();
        System.err.println("只取【test】集合中的第一个文档对象：" + doc.toJson());
    }

    /**
     * 无条件取出（查询）所有的文档对象（所有数据）
     */
    @Test
    public void findAll() {

        /**
         * 要检索集合中的所有文档，我们将使用find()方法，而不需要任何参数。
         * 为了遍历结果，将iterator()方法链接到find()中。
         * 迭代器类似SQL的游标cursor
         */
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            /**
             * 如果迭代到下一个对象等于false，退出迭代，并关闭迭代器
             */
            int index = 0;
            while (cursor.hasNext()) {
                System.err.println((index + 1) + ":" + cursor.next().toJson());
                index++;
            }
        } finally {
            cursor.close();
        }

        System.err.println("=======不推荐下面的遍历法");
        /**
         * 虽然可以允许迭代使用下面的习惯用法，但避免使用它，因为如果循环提前终止，应用程序可能会泄漏一个游标:
         */
        for (Document cur : collection.find()) {
            System.err.println(cur.toJson());
        }
    }


    /**
     * 带条件查询文档对象
     */
    @Test
    public void queryOneOf() {

        /**
         * 要查询匹配某些条件的文档，请将filter对象传递给find()方法。
         * 为了方便创建过滤器对象，Mongo-Java驱动程序提供了过滤器帮助器。
         */

        /**
         * 查询count == 1 的文档对象
         */

        Document doc = collection.find(eq("count", 1)).first();
        if (doc != null) {
            System.err.println("条件1查询结果：" + doc.toJson());
        } else {
            System.err.println("条件1查询结果：无");
        }


        /**
         * 查询count<2  且 name.equals("Mongo")的文档对象,显然不存在
         * 如果查询全部符合条件的文档集合，请使用find(*).iterator()
         */
        doc = collection.find(and(lt("count", 2), eq("name", "Mongo"))).first();
        if (doc != null) {
            System.err.println("条件2查询结果：" + doc.toJson());
        } else {
            System.err.println("条件2查询结果：无");
        }

        /**
         * Filters
         * gt gte  === >  >=
         * lt lte  === <  <=
         * eq      === ==
         * and     === 相当于 sql语句的 where xxx  and xxx
         * or      === 相当于 sql语句的 where xxx  or  xxx
         */
    }

}




