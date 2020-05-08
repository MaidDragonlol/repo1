package com.example.kafkamessage.kafkaclear.getdatafrommongo;

import java.text.ParseException;

public class Test {
    public static void main(String[] args) throws ParseException, IllegalAccessException {

        DataSearch getInstence = new DataSearch();
        /*每段时间日志量*/
        String result = getInstence.logCountStartByTime("2019-02-28 04:10:53");
        /*一段时间的method统计*/
        String result2 = getInstence.MethodTimesByTime("2019-02-28 04:10:53", "2022-02-28 04:10:53");
        /*一段时间的ota接口统计*/
        String result3 = getInstence.OtaTimesByTime("2019-02-28 04:10:53", "2022-02-28 04:10:53");
        /*一段时间的route航线统计*/
        String result4 = getInstence.RouteTimesByTime("2019-02-28 04:10:53", "2022-02-28 04:10:53");
        /*一段时间的route航线错误率统计*/
        String result5 = getInstence.RouteErroFrequncyByTime("2019-02-28 04:10:53", "2022-02-28 04:10:53");
        System.out.println(result + "\n" + result2 + "\n" + result3 + "\n" + result4 + "\n" + result5);
    }

}
