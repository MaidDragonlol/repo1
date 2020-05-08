package aplicationmain;

import datasearch.*;
import kafkaclear.ClearStreamsUtil;
import kafkaclear.KafkaProducerLog;
/*这个是程序总体运行的入口*/
public class MainAplication {
    public static void main(String[] args) throws Exception {
        /*生产者加入数据*/
        KafkaProducerLog.producer();
        /*kafka streams清洗数据*/
        ClearStreamsUtil.Clear();
        /*存入mongo数据库*/
       /*运行kafka包下的 MongoDBSimpleConsumer3;*/
        /*实现功能*/
        /*功能一：统计从开始时间，每隔10分钟日志量，总计100分钟*/
        LogtotalByTime.logtotal("2020-02-28 00:06:10");//输入开始时间
        /*功能二：统计从开始时间，每隔10分钟日志量，总计100分钟*/
        MethodTimesByTime.methodtimes("2020-02-28 03:05:18", "2022-02-28 05:05:18");//输入开始、结束时间
        /*功能三：统计从开始时间，每隔10分钟日志量，总计100分钟*/
        OtaTimesByTime.otatimes("2020-02-28 03:05:18", "2022-02-28 05:05:18");//输入开始、结束时间
        /*功能四：统计从开始时间，每隔10分钟日志量，总计100分钟*/
        RoutErroFrequncyByTime.routerrofrequncy("2020-02-28 03:05:18", "2022-02-28 05:05:18");//输入开始、结束时间
        /*功能五：统计从开始时间，每隔10分钟日志量，总计100分钟*/
        RouteTimesByTime.routetimes("2020-02-28 03:05:18", "2022-02-28 05:05:18");//输入开始、结束时间

    }
}
