package logclearutil;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetObjUtil {
/*判断并获取单个路径属性*/
    public static JSONObject run(JSONObject obj, String node) {
        try {
            if (node.contains("[")) {
                JSONArray arr = obj.getJSONArray(node.substring(0, node.indexOf("[")));
                for (int i = 0; i < arr.length(); i++) {
                    if ((i + "").equals(node.substring(node.indexOf("["), node.indexOf("]")).replace("[", ""))) {
                        return arr.getJSONObject(i);
                    }
                }
            } else {
                return obj.getJSONObject(node);
            }
        } catch (Exception e) {
            return obj;
        }
        return null;
    }
}
