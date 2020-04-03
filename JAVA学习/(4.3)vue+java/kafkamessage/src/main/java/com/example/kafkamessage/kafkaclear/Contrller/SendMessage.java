package com.example.kafkamessage.kafkaclear.Contrller;

import com.example.kafkamessage.kafkaclear.getdatafrommongo.DataSearch;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "", methods = {})
@RestController
public class SendMessage {
    DataSearch getInstence = new DataSearch();

    /*每段时间日志量*//*备份：, method = RequestMethod.POST，"2019-02-28 04:10:53", "2022-02-28 04:10:53"*/
    @RequestMapping(value = "/logCountStartByTime", method = RequestMethod.POST)
    public String logCountStart(@RequestParam String startTime) throws ParseException {
        String result = getInstence.logCountStartByTime(startTime);
        return result;
    }                                                    /*示例：localhost:8090//logCountStartByTime*/

    /*一段时间的method统计*/
    @RequestMapping(value = "/MethodTimesByTime", method = RequestMethod.POST)
    public String MethodTimes(@RequestParam String startTime, String endTime) throws ParseException {
        String result2 = getInstence.MethodTimesByTime(startTime, endTime);
        return result2;
    }

    /*一段时间的ota接口统计*/
    @RequestMapping(value = "/OtaTimesByTime", method = RequestMethod.POST)
    public String OtaTimes(@RequestParam String startTime, String endTime) throws ParseException {
        String result3 = getInstence.OtaTimesByTime(startTime, endTime);
        return result3;
    }

    /*一段时间的route航线统计*/
    @RequestMapping(value = "/RouteTimesByTime", method = RequestMethod.POST)
    public String RouteTimes(@RequestParam String startTime, String endTime) throws ParseException {
        String result4 = getInstence.RouteTimesByTime(startTime, endTime);
        return result4;
    }

    /*一段时间的route航线错误率统计*/
    @RequestMapping(value = "/RouteErroFrequncyByTime", method = RequestMethod.POST)
    public String RouteErroFrequncy(@RequestParam String startTime, String endTime) throws ParseException {
        String result5 = getInstence.RouteErroFrequncyByTime(startTime, endTime);
        return result5;
    }


}
