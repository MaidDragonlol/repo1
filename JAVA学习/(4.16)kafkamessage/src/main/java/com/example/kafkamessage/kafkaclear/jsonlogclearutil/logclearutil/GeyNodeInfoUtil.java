package com.example.kafkamessage.kafkaclear.jsonlogclearutil.logclearutil;

import java.util.List;

/*为需要获取的属性添加路径*/
public class GeyNodeInfoUtil {

    public static List<String> run(List<String> jsonPaths, String mothod){
        if (mothod.equals("TuniuController.query(..)")) {
            jsonPaths.add("JSON.time");
            jsonPaths.add("JSON.message.method");
            jsonPaths.add("JSON.message.elapseMills");
            jsonPaths.add("JSON.message.args[0].fromCity");
            jsonPaths.add("JSON.message.args[0].toCity");
            jsonPaths.add("JSON.message.result.msg");
            jsonPaths.add("JSON.message.ota");
        } else if (mothod.equals("JintongController.recommend(..)")||mothod.equals("SelfController.recommend(..)")||mothod.equals("TrafreeController.recommend(..)")) {
            jsonPaths.add("JSON.time");
            jsonPaths.add("JSON.message.method");
            jsonPaths.add("JSON.message.elapseMills");
            jsonPaths.add("JSON.message.args[0].routing.fromSegments[0].depAirport");
            jsonPaths.add("JSON.message.args[0].routing.fromSegments[0].arrAirport");
            jsonPaths.add("JSON.message.result.msg");
            jsonPaths.add("JSON.message.ota");
        }
        return jsonPaths;
    }
}
