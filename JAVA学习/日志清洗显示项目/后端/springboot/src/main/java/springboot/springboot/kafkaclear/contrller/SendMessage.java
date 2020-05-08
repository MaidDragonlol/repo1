package springboot.springboot.kafkaclear.contrller;


import org.springframework.web.bind.annotation.*;
import springboot.springboot.kafkaclear.getdatafrommongo.DataSearch;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;


@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "", methods = RequestMethod.GET)
@RestController
public class SendMessage {

    DataSearch getInstence = new DataSearch();
    /*每段时间日志量*//*备份：, method = RequestMethod.POST，"2019-02-28 04:10:53", "2022-02-28 04:10:53"*/
    @RequestMapping(value = "/logCountStartByTime", method = RequestMethod.GET)
    public String logCountStart(@RequestParam String startTime, HttpServletResponse response) throws ParseException {
        String result = getInstence.logCountStartByTime(startTime);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return result;
    }                                                    /*示例：localhost:8090//logCountStartByTime*/

    /*一段时间的method统计*/
    @RequestMapping(value = "/MethodTimesByTime", method = RequestMethod.GET)
    public String MethodTimes(@RequestParam String startTime, String endTime, HttpServletResponse response) throws ParseException {
        String result2 = getInstence.MethodTimesByTime(startTime, endTime);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return result2;
    }

    /*一段时间的ota接口统计*/
    @RequestMapping(value = "/OtaTimesByTime", method = RequestMethod.GET)
    public String OtaTimes(@RequestParam String startTime, String endTime) throws ParseException {
        String result3 = getInstence.OtaTimesByTime(startTime, endTime);
        return result3;
    }

    /*一段时间的route航线统计*/
    @RequestMapping(value = "/RouteTimesByTime", method = RequestMethod.GET)
    public String RouteTimes(@RequestParam String startTime, String endTime, HttpServletResponse response) throws ParseException {
        String result4 = getInstence.RouteTimesByTime(startTime, endTime);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return result4;
    }

    /*一段时间的route航线错误率统计*/
    @RequestMapping(value = "/RouteErroFrequncyByTime", method = RequestMethod.GET)
    public String RouteErroFrequncy(@RequestParam String startTime, String endTime, HttpServletResponse response) throws ParseException {
        String result5 = getInstence.RouteErroFrequncyByTime(startTime, endTime);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return result5;
    }


}
