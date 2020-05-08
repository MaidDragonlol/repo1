package kafka.jsonlogclearutil.logclearutil;



import kafka.jsonlogclearutil.logbean.LogCutQuery;
import kafka.jsonlogclearutil.logbean.LogCutRecommend;
import kafka.jsonlogclearutil.logbeanutil.LogCutQueryUtil;
import kafka.jsonlogclearutil.logbeanutil.LogCutRecommendUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*工程运行主方法的封装类，输入JSON获取对应属性*/
public class InsertConentUtil {
    //创建 SingleObject 的一个对象
    private static InsertConentUtil instance = new InsertConentUtil();

    //让构造函数为 private，这样该类就不会被实例化
    private InsertConentUtil() {
    }

    //获取唯一可用的对象
    public static InsertConentUtil getInstance() {
        return instance;
    }

    public String run(String content) throws JSONException {
        /*这波就传了一次也只转了一次*/
        JSONObject obj = new JSONObject(String.valueOf(content));
        /* 先把method拿出来用于判断*/
        String method = GetNodeValueUtil.run(content, "JSON.message.method");
        /*把要提取出的属性对应路径都存入jsonPaths*/
        List<String> jsonPaths = new ArrayList<String>();
        List<String> geyNodeInfo = GeyNodeInfoUtil.run(jsonPaths, method);
        //获取唯一可用的对象
        LogCutQueryUtil logCutQueryUtil = LogCutQueryUtil.getInstance();
        //获取LogCutQyery实例
        LogCutQuery logCutQuery = logCutQueryUtil.run();
        //获取唯一可用的对象
        LogCutRecommendUtil logCutRecommendUtil = LogCutRecommendUtil.getInstance();
        //获取LogCutQyery实例
        LogCutRecommend logCutRecommend = logCutRecommendUtil.run();
        /*遍历jsonPaths读取一一对应属性一一存入标准类*/
        for (String jsonPath : geyNodeInfo) {
            int i = 1;
            String[] everyMap = RecursiveUtil.run(obj, jsonPath, i);
            InsertToBeanUtil insertToBeanUtil = InsertToBeanUtil.getInstance();
            if (method.equals("TuniuController.query(..)")) {
                insertToBeanUtil.runLogCutQuery(logCutQuery, everyMap);
            } else if (method.equals("JintongController.recommend(..)") || method.equals("TrafreeController.recommend(..)") || method.equals("SelfController.recommend(..)")) {
                insertToBeanUtil.runLogCutRecommend(logCutRecommend, everyMap);
            }
        }

        if (method.equals("TuniuController.query(..)")) {
            JSONObject jsonObject = new JSONObject();
            String fromCity = logCutQuery.getFromCity();
            String toCity = logCutQuery.getToCity();
            String route = fromCity + "-" + toCity;
           /* UUID uuid = UUID.randomUUID();
            String str = uuid.toString();
            String uuidStr = str.replace("-", "");
            jsonObject.put("_id", uuidStr);*/
            jsonObject.put("time", logCutQuery.getTime());
            jsonObject.put("msg", logCutQuery.getMsg());
            jsonObject.put("route", route);
            jsonObject.put("method", method);
            jsonObject.put("ota", logCutQuery.getOta());
            String result2 = String.valueOf(jsonObject);
            return result2;

        } else if (method.equals("JintongController.recommend(..)") || method.equals("TrafreeController.recommend(..)") || method.equals("SelfController.recommend(..)")) {
            JSONObject jsonObject2 = new JSONObject();
            String fromCity = logCutRecommend.getDepAirport();
            String toCity = logCutRecommend.getArrAirport();
            String route = fromCity + "-" + toCity;
           /* UUID uuid = UUID.randomUUID();
            String str = uuid.toString();
            String uuidStr = str.replace("-", "");
            jsonObject2.put("_id", uuidStr);*/
            jsonObject2.put("time", logCutRecommend.getTime());
            jsonObject2.put("msg", logCutRecommend.getMsg());
            jsonObject2.put("route", route);
            jsonObject2.put("method", logCutRecommend.getMethod());
            jsonObject2.put("ota", logCutRecommend.getOta());
            String result2 = String.valueOf(jsonObject2);
            return result2;

        }
        return null;
    }
}
