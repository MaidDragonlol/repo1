package kafkastreamlogs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.UUID;

public class Test2 {
    public static void main(String[] args) {
        String route = "ota";
        String routeTotalTimes = "11";
        String routeErrorTimes = "2";
        String logjJosn = "{" +
                "        \"route\":\""+route+"\"," +
                "        \"routeTotalTimes\":\"\""+routeTotalTimes+"\"," +
                "        \"routeErrorTimes\":\""+routeErrorTimes+"\"" +
                "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("route",route);
        jsonObject.put("routeTotalTimes",routeTotalTimes);
        jsonObject.put("routeErrorTimes",routeErrorTimes);
        String valuePartString = jsonObject.toString();
        JSONObject a = JSON.parseObject(valuePartString);
        System.out.println(a);
        System.out.println(valuePartString);
        System.out.println(UUID.randomUUID());
    }


}
