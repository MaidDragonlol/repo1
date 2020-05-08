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

    public String run(String content) throws JSONException {
        /*这波就传了一次也只转了一次*/
        JSONObject obj = new JSONObject(String.valueOf(content));
        /* 先把method拿出来用于判断*/
        GetNodeValueUtil getNodeValueUtil = GetNodeValueUtil.getInstance();
        String mothod = getNodeValueUtil.run(content, "JSON.message.method");
        /*把要提取出的属性对应路径都存入jsonPaths*/
        List<String> jsonPaths = new ArrayList<String>();
        GeyNodeInfoUtil geyNodeInfoUtil = GeyNodeInfoUtil.getInstance();
        List<String> geyNodeInfo = geyNodeInfoUtil.run(jsonPaths, mothod);
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
            RecursiveUtil recursiveUtil = RecursiveUtil.getInstance();
            String[] everyMap = recursiveUtil.run(obj, jsonPath, i);
            InsertToBeanUtil insertToBeanUtil = InsertToBeanUtil.getInstance();

            if (mothod.equals("TuniuController.query(..)")) {
                insertToBeanUtil.runLogCutQuery(logCutQuery, everyMap);
            } else if (mothod.equals("JintongController.recommend(..)")) {
                insertToBeanUtil.runLogCutRecommend(logCutRecommend, everyMap);
            }
        }
        if (mothod.equals("TuniuController.query(..)")) {
            return logCutQuery.toString();
        } else if (mothod.equals("JintongController.recommend(..)")) {
            return logCutRecommend.toString();
        }
        return null;
    }
}
