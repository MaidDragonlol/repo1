package kafkastreamlogs;


import logbeen.LogCutClear;
import org.json.JSONArray;
import org.json.JSONObject;

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


    /*获取节点值*/
    public static synchronized String getNodeValue(String jsonContent, String jsonPath) throws Exception {
        String[] nodes = jsonPath.split("\\.");
        JSONObject obj = new JSONObject(String.valueOf(jsonContent));

        for (int i = 1; i < nodes.length; i++) {
            if (obj != null) {
                obj = getObj(obj, nodes[i]);
            }

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
/*这底下是日志清理的单机实验*/
    public static void main(String[] args) throws Exception {
        LogCutClear logCutQuery = new LogCutClear();
        LogCutClear logCutRecommend = new LogCutClear();

        /*用switch选择接入方法*/
        String jsonContent = "{\"time\":\"2020-02-28 10:18:58\", \"thread\":\"http-nio-8000-exec-24\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T10:18:56.753\",\"startMills\":1582856336753,\"elapseMills\":2243,\"Args\":[{\"cid\":\"cxtuniu\",\"tripType\":1,\"fromCity\":\"DFW\",\"toCity\":\"CHI\",\"fromDate\":\"20200228\",\"retDate\":\"\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_lack_of_data_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}";
        String jsonContent2 = "{\"time\":\"2020-02-28 10:23:51\", \"thread\":\"http-nio-8000-exec-18\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"JintongController.recommend(..)\",\"startTime\":\"2020-02-28T10:23:48.839\",\"startMills\":1582856628839,\"elapseMills\":2397,\"args\":[{\"cid\":\"cxjinri\",\"tripType\":1,\"adultNum\":1,\"childrenNum\":0,\"routing\":{\"adultPrice\":0,\"adultTax\":0,\"adultTaxType\":0,\"applyType\":0,\"priceType\":0,\"autoTicket\":0,\"childPrice\":0,\"childTax\":0,\"ticketTimeLimit\":0,\"childTaxType\":0,\"infantPrice\":0,\"infantTax\":0,\"data\":\"123456\",\"fareType\":null,\"lastTicketDate\":null,\"nationality\":null,\"needChangePNR\":null,\"speFlag\":0,\"reservationType\":null,\"ticketType\":null,\"validatingCarrier\":null,\"ticketInvoiceType\":0,\"ticketInfo\":null,\"rule\":null,\"ruleEx\":null,\"fromSegments\":[{\"aircraftCode\":\"\",\"arrAirport\":\"PVG\",\"arrTime\":\"202003030945\",\"arrivingTerminal\":null,\"baggageAllowance\":0,\"baggagePieces\":0,\"cabin\":\"L\",\"cabinClass\":1,\"carrier\":\"SU\",\"codeShare\":false,\"depAirport\":\"SVO\",\"depTime\":\"202003021950\",\"duration\":0,\"flightNumber\":\"SU208\",\"seatCount\":0,\"stopCities\":\"\",\"sharingFlightNumber\":null,\"departureTerminal\":null}],\"retSegments\":[],\"fromLuggages\":null,\"retLuggages\":null},\"passengers\":[{\"name\":\"BILA/NATALIIA\",\"ageType\":0,\"birthday\":\"19890808\",\"gender\":\"F\",\"cardType\":\"PP\",\"cardNum\":\"E12369898\",\"cardExpired\":\"20280320\",\"cardIssuePlace\":\"UA\",\"nationality\":\"UA\"}]}],\"result\":{\"status\":0,\"msg\":\"无运价\",\"sessionId\":null,\"routing\":null,\"maxSeats\":0,\"rule\":null,\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"JINTONG\",\"stage\":\"RECOMMEND\",\"path\":\"/jinri/flight/v1/recommend\",\"remoteAddr\":\"61.151.247.215\"}}";
        String mothod = JsonPaser.getNodeValue(jsonContent2, "JSON.message.method");
        switch (mothod) {
            case "TuniuController.query(..)": {
                String time = JsonPaser.getNodeValue(jsonContent, "JSON.time");
                String method = JsonPaser.getNodeValue(jsonContent, "JSON.message.method");
                String elapseMills = JsonPaser.getNodeValue(jsonContent, "JSON.message.elapseMills");
                String fromCity = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.[fromCity]");
                String toCity = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.[toCity]");
                String msg = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.result.msg");
                String ota = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.ota");
                /*fromcity和tocity结果不对。。。;*/
                System.out.println("time\n" + time + "\nmethod\n" + method + "\nelapseMills\n" + elapseMills + "\nfromCity\n" + fromCity + "\ntoCity\n" + toCity + "\nmsg\n" + msg + "\nota\n" + ota);
                logCutQuery.setTime(time);
                logCutQuery.setMethod(method);
                logCutQuery.setElapseMills(elapseMills);
                logCutQuery.setFromCity(fromCity);
                logCutQuery.setToCity(toCity);
                logCutQuery.setMsg(msg);
                logCutQuery.setOta(ota);
                System.out.println(logCutQuery);
            }
            break;
            case "JintongController.recommend(..)": {
                String time = JsonPaser.getNodeValue(jsonContent, "JSON.time");
                String method = JsonPaser.getNodeValue(jsonContent, "JSON.message.method");
                String elapseMills = JsonPaser.getNodeValue(jsonContent, "JSON.message.elapseMills");
                String fromCity = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.[routing.depAirport]");
                String toCity = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.[routing.stopCities]");
                String msg = JsonPaser.getNodeValue(jsonContent, "JSON.message.result.msg");
                String ota = JsonPaser.getNodeValue(jsonContent, "JSON.message.ota");

                System.out.println("time\n" + time + "\nmethod\n" + method + "\nelapseMills\n" + elapseMills + "\nfromCity\n" + fromCity + "\ntoCity\n" + toCity + "\nmsg\n" + msg + "\nota\n" + ota);
                logCutRecommend.setTime(time);
                logCutRecommend.setMethod(method);
                logCutRecommend.setElapseMills(elapseMills);
                logCutRecommend.setFromCity(fromCity);
                logCutRecommend.setToCity(toCity);
                logCutRecommend.setMsg(msg);
                logCutRecommend.setOta(ota);
            }
            break;
            default:
                System.out.println("没有");
        }


    }

}