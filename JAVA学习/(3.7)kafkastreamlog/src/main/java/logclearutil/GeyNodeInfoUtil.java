package logclearutil;

import java.util.List;
/*为需要获取的属性添加路径*/
public class GeyNodeInfoUtil {
    //创建 SingleObject 的一个对象
    private static GeyNodeInfoUtil instance = new GeyNodeInfoUtil();

    //让构造函数为 private，这样该类就不会被实例化
    private GeyNodeInfoUtil(){}

    //获取唯一可用的对象
    public static GeyNodeInfoUtil getInstance(){
        return instance;
    }

    public List<String> run(List<String> jsonPaths, String mothod){
        if (mothod.equals("TuniuController.query(..)")) {
            jsonPaths.add("JSON.time");
            jsonPaths.add("JSON.message.method");
            jsonPaths.add("JSON.message.elapseMills");
            jsonPaths.add("JSON.message.args[0].fromCity");
            jsonPaths.add("JSON.message.args[0].toCity");
            jsonPaths.add("JSON.message.result.msg");
            jsonPaths.add("JSON.message.ota");
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
}
