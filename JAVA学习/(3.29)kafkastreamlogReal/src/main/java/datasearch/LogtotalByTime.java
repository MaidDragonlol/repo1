package datasearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import getdatafrommongo.GetJsons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*统计从开始时间，每隔10分钟日志量，总计100分钟*/
public class LogtotalByTime {
    public static String logtotal(String datebegin) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datebegin2 = format.parse(datebegin);
        JSONObject logTotal = new JSONObject();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        String data = GetJsons.getJsons();
       /* String data = DataTest.getdatas();*/
        String[] jsonObjects = data.split("\n");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datebegin2);
        for (int i = 0; i < 10; i++) {
            Integer count = 1;
            Date startDate = calendar.getTime();
            calendar.add(Calendar.MINUTE, 10);
            Date endDate = calendar.getTime();
            for (String jsonObject : jsonObjects
            ) {
                JSONObject jsonObject1 = JSON.parseObject(jsonObject);
                String time = String.valueOf(jsonObject1.get("time"));
                Date date = format.parse(time);
                int compare1 = date.compareTo(startDate);
                int compare2 = date.compareTo(endDate);
                if (compare1 < 0 && compare2 > 0) count++;
            }
            logTotal.put(String.valueOf(startDate), count);
        }
        String result = logTotal.toString();
        return result;
    }
    /*测试*/
    public static void main(String[] args) throws ParseException {
        String test = logtotal("2020-02-28 00:06:10");
        System.out.println(test);
    }
}
