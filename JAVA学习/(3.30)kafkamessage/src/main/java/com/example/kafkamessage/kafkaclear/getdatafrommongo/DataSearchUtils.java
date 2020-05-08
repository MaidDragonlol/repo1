package com.example.kafkamessage.kafkaclear.getdatafrommongo;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;

public class DataSearchUtils {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("clusterdb");
    MongoCollection<Document> collection = database.getCollection("fish");

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

    public String logCountStartByTime(String startTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject logTotal = new JSONObject();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        Date date = format.parse(startTime);
        Integer countAll = 0;
        Bson filter = Filters.gt("time", startTime);
        FindIterable findIterable = collection.find(filter);
        MongoCursor<Document> cursor = findIterable.iterator();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < 12; i++) {
            Integer count = 1;
            Date startDate = calendar.getTime();
            calendar.add(Calendar.MINUTE, 10);
            Date endDate = calendar.getTime();
            while (cursor.hasNext()) {
                String time = cursor.next().getString("time");
                countAll++;//统计总日志量-----还没用上
                Date dateNow = format.parse(time);
                int compare1 = dateNow.compareTo(startDate);
                int compare2 = dateNow.compareTo(endDate);
                if (compare1 < 0 && compare2 > 0) count++;
            }
            logTotal.put(String.valueOf(startDate), count);
        }
        String result = logTotal.toString();
        return result;
            /*System.out.println(cursor.next());
            System.out.println(i);*/
    }

    public String MethodTimesByTime(String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datebegin = format.parse(startTime);
        Date dateend = format.parse(endTime);
        JSONObject methodsort10 = new JSONObject();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        Bson filter = Filters.gt("time", startTime);
        FindIterable findIterable = collection.find(filter);
        MongoCursor<Document> cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            String time = cursor.next().getString("time");
            Date date = format.parse(time);
            int compare1 = date.compareTo(datebegin);
            int compare2 = date.compareTo(dateend);
            if (compare1 < 0 || compare2 > 0) continue;
            String method = String.valueOf(cursor.next().get("method"));
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
        List<String> keys = sortMapByValue(maps);
        /*取出前十个存入JSON*/
        for (int i = 0; i < 10 && i < keys.size(); i++) {
            String key = keys.get(i);
            Integer value = maps.get(key);
            methodsort10.put(key, value);
        }
        String methodOut = String.valueOf(methodsort10);
        return methodOut;
    }

    public String OtaTimesByTime(String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datebegin = format.parse(startTime);
        Date dateend = format.parse(endTime);
        JSONObject otasort10 = new JSONObject();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        Bson filter = Filters.gt("time", startTime);
        FindIterable findIterable = collection.find(filter);
        MongoCursor<Document> cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            String time = cursor.next().getString("time");
            Date date = format.parse(time);
            int compare1 = date.compareTo(datebegin);
            int compare2 = date.compareTo(dateend);
            if (compare1 < 0 || compare2 > 0) continue;
            String ota = String.valueOf(cursor.next().get("ota"));
            Integer jundge = maps.putIfAbsent(ota, 1);
            if (jundge != null) {
                Iterator<String> iter = maps.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Integer value = maps.get(key);
                    if (key.equals(ota)) {
                        maps.put(ota, ++value);
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
            otasort10.put(key, value);
        }
        String otaOut = String.valueOf(otasort10);
        return otaOut;
    }

    public String RouteTimesByTime(String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datebegin = format.parse(startTime);
        Date dateend = format.parse(endTime);
        JSONObject routesort10 = new JSONObject();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        Bson filter = Filters.gt("time", startTime);
        FindIterable findIterable = collection.find(filter);
        MongoCursor<Document> cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            String time = cursor.next().getString("time");
            Date date = format.parse(time);
            int compare1 = date.compareTo(datebegin);
            int compare2 = date.compareTo(dateend);
            if (compare1 < 0 || compare2 > 0) continue;
            String route = String.valueOf(cursor.next().get("route"));
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

    public String RouteErroFrequncyByTime(String startTime, String endTime) throws ParseException {
        Integer errorTime = 1;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datebegin2 = format.parse(startTime);
        Date dateend2 = format.parse(endTime);
        /*List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();*/
        JSONObject routesort10 = new JSONObject();
        Map<String, Integer> maps = new HashMap<String, Integer>();//航线次数
        Map<String, Integer> maps2 = new HashMap<String, Integer>();//错误次数
        Bson filter = Filters.gt("time", startTime);
        FindIterable findIterable = collection.find(filter);
        MongoCursor<Document> cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            String time = cursor.next().getString("time");
            Date date = format.parse(time);
            int compare1 = date.compareTo(datebegin2);
            int compare2 = date.compareTo(dateend2);
            if (compare1 < 0 || compare2 > 0) continue;
            String route = String.valueOf(cursor.next().get("route"));
            String msg = String.valueOf(cursor.next().get("msg"));
            if (msg.equals("success")) {
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
                        maps.put(route, errovalue + errorTime);
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
        String methodOut = String.valueOf(routesort10);
        return methodOut;
    }

    /*保留方法。留待备用*/

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




