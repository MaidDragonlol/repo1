package com.example.kafkamessage.kafkaclear.logclearutil;

import org.json.JSONException;
import org.json.JSONObject;

/*递归遍历路径*/
public class RecursiveUtil {

    public static String[] run(JSONObject obj, String jsonPath, int i) throws JSONException {
        String[] nodes = jsonPath.split("\\.");
        JSONObject objElemet = GetObjUtil.run(obj, nodes[i]);
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
