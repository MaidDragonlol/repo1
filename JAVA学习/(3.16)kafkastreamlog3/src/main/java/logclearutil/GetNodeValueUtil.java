package logclearutil;

import org.json.JSONException;
import org.json.JSONObject;
/*获取对应属性的值*/
public class GetNodeValueUtil {
    public static String run(String jsonContent, String jsonPath) throws JSONException {
        String[] nodes = jsonPath.split("\\.");
        JSONObject obj = new JSONObject(String.valueOf(jsonContent));
        /*遍历每一个nodes*/
        for (int i = 1; i < nodes.length; i++) {
            if (obj != null) {
                obj = GetObjUtil.run(obj, nodes[i]);
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
