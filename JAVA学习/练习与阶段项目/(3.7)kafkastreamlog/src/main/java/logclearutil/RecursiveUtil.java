package logclearutil;

import org.json.JSONException;
import org.json.JSONObject;
/*递归遍历路径*/
public class RecursiveUtil {
    //创建 SingleObject 的一个对象
    private static RecursiveUtil instance = new RecursiveUtil();

    //让构造函数为 private，这样该类就不会被实例化
    private RecursiveUtil(){}

    //获取唯一可用的对象
    public static RecursiveUtil getInstance(){
        return instance;
    }

    public String[] run(JSONObject obj, String jsonPath, int i) throws JSONException {
        String[] nodes = jsonPath.split("\\.");
        //获取唯一可用的对象
        GetObjUtil getObjUtil = GetObjUtil.getInstance();
        JSONObject objElemet = getObjUtil.run(obj, nodes[i]);
        if (i < nodes.length - 1) {
            i++;
            return run(objElemet, jsonPath, i);
        }
        if ((i + 1) == nodes.length) {
            String everyContent = objElemet.getString(nodes[i]);
            /*这里把键值对存入map后面存入标准类的时候判断要用*/
            String[] everyMap = new String[]{nodes[i],everyContent};
            return everyMap;
        }

        return null;
    }
}
