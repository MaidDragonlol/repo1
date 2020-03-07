package logclearutil;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetObjUtil {
    //创建 SingleObject 的一个对象
    private static GetObjUtil instance = new GetObjUtil();

    //让构造函数为 private，这样该类就不会被实例化
    private GetObjUtil() {
    }

    //获取唯一可用的对象
    public static GetObjUtil getInstance() {
        return instance;
    }
/*判断并获取单个路径属性*/
    public JSONObject run(JSONObject obj, String node) {
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
