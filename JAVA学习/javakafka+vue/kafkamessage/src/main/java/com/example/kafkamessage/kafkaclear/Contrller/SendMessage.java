package com.example.kafkamessage.kafkaclear.Contrller;

import com.example.kafkamessage.kafkaclear.getdatafrommongo.DataSearch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class SendMessage {
    DataSearch getInstence = new DataSearch();

    /*每段时间日志量*//*备份：, method = RequestMethod.POST*/
    @RequestMapping(value = "/logCountStartByTime")
    public String logCountStart() throws ParseException {
        String result = getInstence.logCountStartByTime("2019-02-28 04:10:53");
        return result;
    }                                                    /*示例：localhost:8080//logCountStartByTime*/

    /*一段时间的method统计*/
    @RequestMapping(value = "/MethodTimesByTime", method = RequestMethod.POST)
    public String MethodTimes() throws ParseException {
        String result2 = getInstence.MethodTimesByTime("2019-02-28 04:10:53", "2022-02-28 04:10:53");
        return result2;
    }

    /*一段时间的ota接口统计*/
    @RequestMapping(value = "/OtaTimesByTime", method = RequestMethod.POST)
    public String OtaTimes() throws ParseException {
        String result3 = getInstence.OtaTimesByTime("2019-02-28 04:10:53", "2022-02-28 04:10:53");
        return result3;
    }

    /*一段时间的route航线统计*/
    @RequestMapping(value = "/RouteTimesByTime", method = RequestMethod.POST)
    public String RouteTimes() throws ParseException {
        String result4 = getInstence.RouteTimesByTime("2019-02-28 04:10:53", "2022-02-28 04:10:53");
        return result4;
    }

    /*一段时间的route航线错误率统计*/
    @RequestMapping(value = "/RouteErroFrequncyByTime", method = RequestMethod.POST)
    public String RouteErroFrequncy() throws ParseException {
        String result5 = getInstence.RouteErroFrequncyByTime("2019-02-28 04:10:53", "2022-02-28 04:10:53");
        return result5;
    }


}
