package logclearutil;

import org.json.JSONException;
import org.json.JSONObject;
/*获取对应属性的值*/
public class GetNodeValueUtil {
    //创建 SingleObject 的一个对象
    private static GetNodeValueUtil instance = new GetNodeValueUtil();

    //让构造函数为 private，这样该类就不会被实例化
    private GetNodeValueUtil() {
    }

    //获取唯一可用的对象
    public static GetNodeValueUtil getInstance() {
        return instance;
    }

    public String run(String jsonContent, String jsonPath) throws JSONException {
        String[] nodes = jsonPath.split("\\.");
        JSONObject obj = new JSONObject(String.valueOf(jsonContent));
        /*遍历每一个nodes*/
        for (int i = 1; i < nodes.length; i++) {
            if (obj != null) {
                GetObjUtil object = GetObjUtil.getInstance();
                obj = object.run(obj, nodes[i]);
            }
            /* 逐次获取值并返回*/
            if ((i + 1) == nodes.length) {
                try {
                    return obj.getString(nodes[i]);
                } catch (Exception e) {
                    return "JSONException:" + e.getMessage() + ",NodeString:" + obj.toString();
                }
            }
        }
        return null;


    }

}
