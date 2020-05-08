package kafkastreamlogs;

import logbean.LogCutQuery;
import logbean.LogCutRecommend;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*json格式读取方法类*/
public class JsonPaser {

    /*对节点进行解析*/
    private static JSONObject getObj(JSONObject obj, String node) {
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

    /*获取节点值,留下来是为了先用来判断method*/
    private static synchronized String getNodeValue(String jsonContent, String jsonPath) throws Exception {
        String[] nodes = jsonPath.split("\\.");
        JSONObject obj = new JSONObject(String.valueOf(jsonContent));
        /*遍历每一个nodes*/
        for (int i = 1; i < nodes.length; i++) {
            if (obj != null) {
                obj = getObj(obj, nodes[i]);
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


    /*操作的主方法，输入JSON提取属性存入对应的标准类，这里传String进去转成JSONObject*/
    public static String insertConent(String content) throws Exception {
        LogCutQuery logCutQuery = new LogCutQuery();
        LogCutRecommend logCutRecommend = new LogCutRecommend();
        /*这波就传了一次也只转了一次*/
        JSONObject obj = new JSONObject(String.valueOf(content));
        /* 先把method拿出来用于判断*/
        String mothod = JsonPaser.getNodeValue(content, "JSON.message.method");
        /*把要提取出的属性对应路径都存入jsonPaths*/
        /*String[] jsonPaths = new String[7];*/
        List<String> jsonPaths = new ArrayList<String>();
        /*{"JSON.time","JSON.message.method","JSON.message.elapseMills","JSON.message.args[0].routing.fromSegments[0].depAirport","JSON.message.args[0].routing.fromSegments[0].arrivingTerminal","JSON.message.result.msg","JSON.message.ota"};
         */
        /*判断method再选用合适和日志格式*//*这里为什么读不进去啊。。。。。。*/
        if (mothod == "TuniuController.query(..)") {
            jsonPaths.add("JSON.time");
            jsonPaths.add("JSON.message.method");
            jsonPaths.add("JSON.message.elapseMills");
            jsonPaths.add("JSON.message.args[0].fromCity");
            jsonPaths.add("JSON.message.args[0].toCity");
            jsonPaths.add("JSON.message.args[0].result.msg");
            jsonPaths.add("JSON.message.args[0].ota");
        } else if (mothod == "JintongController.recommend(..)") {
            jsonPaths.add("JSON.time");
            jsonPaths.add("JSON.message.method");
            jsonPaths.add("JSON.message.elapseMills");
            jsonPaths.add("JSON.message.args[0].routing.fromSegments[0].depAirport");
            jsonPaths.add("JSON.message.args[0].routing.fromSegments[0].arrivingTerminal");
            jsonPaths.add("JSON.message.result.msg");
            jsonPaths.add("JSON.message.ota");
        }
        /*遍历jsonPaths读取一一对应属性一一存入标准类*/
        for (String jsonPath : jsonPaths) {
            String[] nodes = jsonPath.split("\\.");
            for (int i = 1; i < nodes.length; i++) {
                if (obj != null) {
                    obj = getObj(obj, nodes[i]);
                }

                if ((i + 1) == nodes.length) {
                    try {
                        String everyContent = obj.getString(nodes[i]);
                        /*元素提取后判断根据method如何哪个标准类*/
                        if (mothod == "TuniuController.query(..)") {
                            switch (everyContent) {
                                case "time": {
                                    logCutQuery.setTime(everyContent);
                                }
                                break;
                                case "method": {
                                    logCutQuery.setMethod(everyContent);
                                }
                                break;
                                case "elapseMills": {
                                    logCutQuery.setElapseMills(everyContent);
                                }
                                break;
                                case "fromCity": {
                                    logCutQuery.setFromCity(everyContent);
                                }
                                break;
                                case "toCity": {
                                    logCutQuery.setToCity(everyContent);
                                }
                                break;
                                case "msg": {
                                    logCutQuery.setMsg(everyContent);
                                }
                                break;
                                case "ota": {
                                    logCutQuery.setOta(everyContent);
                                }
                                break;
                            }
                        } else if (mothod == "JintongController.recommend(..)") {
                            switch (everyContent) {
                                case "time": {
                                    logCutRecommend.setTime(everyContent);
                                }
                                break;
                                case "method": {
                                    logCutRecommend.setMethod(everyContent);
                                }
                                break;
                                case "elapseMills": {
                                    logCutRecommend.setElapseMills(everyContent);
                                }
                                break;
                                case "msg": {
                                    logCutRecommend.setMsg(everyContent);
                                }
                                break;
                                case "ota": {
                                    logCutRecommend.setOta(everyContent);
                                }
                                break;
                                case "depAirport": {
                                    logCutRecommend.setDepAirport(everyContent);
                                }
                                break;
                                case "stopCities": {
                                    logCutRecommend.setStopCities(everyContent);
                                }
                                break;
                            }
                        }


                    } catch (Exception e) {
                        return "JSONException:" + e.getMessage() + ",NodeString:" + obj.toString();
                    }
                }
            }
        }
        System.out.println(logCutQuery);
        return null;
    }



}

