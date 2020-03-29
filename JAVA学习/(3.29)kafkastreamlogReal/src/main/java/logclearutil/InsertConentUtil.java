package logclearutil;

import logbean.LogCutQuery;
import logbean.LogCutRecommend;
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

    public List<String> run(String content) throws JSONException {
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
            } else if (method.equals("JintongController.recommend(..)")) {
                insertToBeanUtil.runLogCutRecommend(logCutRecommend, everyMap);
            }
        }
        if (method.equals("TuniuController.query(..)")) {
            List<String> stringArryQuery = new ArrayList<>();
            stringArryQuery.add(logCutQuery.getTime());
            stringArryQuery.add(logCutQuery.getMsg());
            stringArryQuery.add(logCutQuery.getFromCity());
            stringArryQuery.add(logCutQuery.getToCity());
            stringArryQuery.add(logCutQuery.getMethod());
            stringArryQuery.add(logCutQuery.getOta());

            return stringArryQuery;
        } else if (method.equals("JintongController.recommend(..)")) {
            List<String> stringArryRecommend = new ArrayList<>();
            stringArryRecommend.add(logCutRecommend.getTime());
            stringArryRecommend.add(logCutRecommend.getMsg());
            stringArryRecommend.add(logCutRecommend.getDepAirport());
            stringArryRecommend.add(logCutRecommend.getStopCities());
            stringArryRecommend.add(logCutRecommend.getMethod());
            stringArryRecommend.add(logCutRecommend.getOta());
            return stringArryRecommend;
        }
        return null;
    }
}
