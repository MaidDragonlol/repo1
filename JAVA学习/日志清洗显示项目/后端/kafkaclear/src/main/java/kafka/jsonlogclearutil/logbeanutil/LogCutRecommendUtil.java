package kafka.jsonlogclearutil.logbeanutil;


import kafka.jsonlogclearutil.logbean.LogCutRecommend;

/*LogCutRecommend的封装类*/
public class LogCutRecommendUtil {
    //创建 SingleObject 的一个对象
    private static LogCutRecommendUtil instance = new LogCutRecommendUtil();

    //让构造函数为 private，这样该类就不会被实例化
    private LogCutRecommendUtil(){}

    //获取唯一可用的对象
    public static LogCutRecommendUtil getInstance(){
        return instance;
    }

    public LogCutRecommend run(){
         LogCutRecommend logCutRecommend = new LogCutRecommend();
         return logCutRecommend;
    }
}
