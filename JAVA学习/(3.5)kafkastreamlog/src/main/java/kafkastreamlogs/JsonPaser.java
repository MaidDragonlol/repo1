package kafkastreamlogs;

import logbean.LogCutQuery;
import logbean.LogCutRecommend;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*json格式读取方法类*/
public class JsonPaser {
    static LogCutQuery logCutQuery = new LogCutQuery();
    static LogCutRecommend logCutRecommend = new LogCutRecommend();
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

    /*判断method再选用合适和日志格式*/
    public static List<String> geyNodeInfo(List<String> jsonPaths, String mothod) {
        if (mothod.equals("TuniuCo{ntroller.query(..)")) {
            jsonPaths.add("JSON.time");
            jsonPaths.add("JSON.message.method");
            jsonPaths.add("JSON.message.elapseMills");
            jsonPaths.add("JSON.message.args[0].fromCity");
            jsonPaths.add("JSON.message.args[0].toCity");
            jsonPaths.add("JSON.message.args[0].result.msg");
            jsonPaths.add("JSON.message.args[0].ota");
        } else if (mothod.equals("JintongController.recommend(..)")) {
            jsonPaths.add("JSON.time");
            jsonPaths.add("JSON.message.method");
            jsonPaths.add("JSON.message.elapseMills");
            jsonPaths.add("JSON.message.args[0].routing.fromSegments[0].depAirport");
            jsonPaths.add("JSON.message.args[0].routing.fromSegments[0].arrivingTerminal");
            jsonPaths.add("JSON.message.result.msg");
            jsonPaths.add("JSON.message.ota");
        }
        return jsonPaths;
    }

    public static void InsertToBean(String mothod, String[] everyMap) {
        String node = everyMap[0];
        String everyContent = everyMap[1];

        if (mothod.equals("TuniuController.query(..)")) {
            switch (node) {
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
        } else if (mothod.equals("JintongController.recommend(..)")) {

            switch (node) {
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

    }
/*这个进行递归运算*/
    public static String[] recursive(JSONObject obj, String jsonPath, int i) throws JSONException {
        String[] nodes = jsonPath.split("\\.");
        JSONObject objElemet = getObj(obj, nodes[i]);
        if (i < nodes.length - 1) {
            i++;
            return recursive(objElemet, jsonPath, i);
        }
        if ((i + 1) == nodes.length) {
            String everyContent = objElemet.getString(nodes[i]);
            /*这里把键值对存入map后面存入标准类的时候判断要用*/
            String[] everyMap = new String[]{nodes[i],everyContent};
            return everyMap;
        }

        return null;

    }


    /*操作的主方法，输入JSON提取属性存入对应的标准类，这里传String进去转成JSONObject*/
    public static String insertConent(String content) throws Exception {

        /*这波就传了一次也只转了一次*/
        JSONObject obj = new JSONObject(String.valueOf(content));
        /* 先把method拿出来用于判断*/
        String mothod = JsonPaser.getNodeValue(content, "JSON.message.method");
        /*把要提取出的属性对应路径都存入jsonPaths*/
        List<String> jsonPaths = new ArrayList<String>();

        /*{"JSON.time","JSON.message.method","JSON.message.elapseMills","JSON.message.args[0].routing.fromSegments[0].depAirport","JSON.message.args[0].routing.fromSegments[0].arrivingTerminal","JSON.message.result.msg","JSON.message.ota"};
         */
        geyNodeInfo(jsonPaths, mothod);
        /*遍历jsonPaths读取一一对应属性一一存入标准类*/
        for (String jsonPath : jsonPaths) {
            String[] everyMap = recursive(obj, jsonPath, 1);
            InsertToBean(mothod, everyMap);
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        /*统计这个程序完成时间*/
        long start = System.nanoTime();//获取系统排序的时间点
        /*这里选一个变量输入观察结果*/
        String jsonContent2 = "{\"time\":\"2020-02-28 10:18:58\", \"thread\":\"http-nio-8000-exec-24\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T10:18:56.753\",\"startMills\":1582856336753,\"elapseMills\":2243,\"Args\":[{\"cid\":\"cxtuniu\",\"tripType\":1,\"fromCity\":\"DFW\",\"toCity\":\"CHI\",\"fromDate\":\"20200228\",\"retDate\":\"\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_lack_of_data_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}";
        String jsonContent = "{\"time\":\"2020-02-28 10:23:51\", \"thread\":\"http-nio-8000-exec-18\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"JintongController.recommend(..)\",\"startTime\":\"2020-02-28T10:23:48.839\",\"startMills\":1582856628839,\"elapseMills\":2397,\"args\":[{\"cid\":\"cxjinri\",\"tripType\":1,\"adultNum\":1,\"childrenNum\":0,\"routing\":{\"adultPrice\":0,\"adultTax\":0,\"adultTaxType\":0,\"applyType\":0,\"priceType\":0,\"autoTicket\":0,\"childPrice\":0,\"childTax\":0,\"ticketTimeLimit\":0,\"childTaxType\":0,\"infantPrice\":0,\"infantTax\":0,\"data\":\"123456\",\"fareType\":null,\"lastTicketDate\":null,\"nationality\":null,\"needChangePNR\":null,\"speFlag\":0,\"reservationType\":null,\"ticketType\":null,\"validatingCarrier\":null,\"ticketInvoiceType\":0,\"ticketInfo\":null,\"rule\":null,\"ruleEx\":null,\"fromSegments\":[{\"aircraftCode\":\"\",\"arrAirport\":\"PVG\",\"arrTime\":\"202003030945\",\"arrivingTerminal\":null,\"baggageAllowance\":0,\"baggagePieces\":0,\"cabin\":\"L\",\"cabinClass\":1,\"carrier\":\"SU\",\"codeShare\":false,\"depAirport\":\"SVO\",\"depTime\":\"202003021950\",\"duration\":0,\"flightNumber\":\"SU208\",\"seatCount\":0,\"stopCities\":\"\",\"sharingFlightNumber\":null,\"departureTerminal\":null}],\"retSegments\":[],\"fromLuggages\":null,\"retLuggages\":null},\"passengers\":[{\"name\":\"BILA/NATALIIA\",\"ageType\":0,\"birthday\":\"19890808\",\"gender\":\"F\",\"cardType\":\"PP\",\"cardNum\":\"E12369898\",\"cardExpired\":\"20280320\",\"cardIssuePlace\":\"UA\",\"nationality\":\"UA\"}]}],\"result\":{\"status\":0,\"msg\":\"无运价\",\"sessionId\":null,\"routing\":null,\"maxSeats\":0,\"rule\":null,\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"JINTONG\",\"stage\":\"RECOMMEND\",\"path\":\"/jinri/flight/v1/recommend\",\"remoteAddr\":\"61.151.247.215\"}}";
        JsonPaser.insertConent(jsonContent);

        long end = System.nanoTime();//获取系统结束的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end - start);//得到所用的时间
        System.out.println(logCutRecommend);
        System.out.println(logCutQuery);
        System.out.println(ms + "ms");  //7ms
    }

}

