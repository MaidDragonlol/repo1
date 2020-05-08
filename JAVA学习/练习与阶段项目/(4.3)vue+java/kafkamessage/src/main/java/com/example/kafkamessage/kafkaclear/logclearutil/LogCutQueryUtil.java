package com.example.kafkamessage.kafkaclear.logclearutil;
import com.example.kafkamessage.kafkaclear.logbean.LogCutQuery;

/*LogCutQuery的封装类*/
public class LogCutQueryUtil {
    //创建 SingleObject 的一个对象
    private static LogCutQueryUtil instance = new LogCutQueryUtil();

    //让构造函数为 private，这样该类就不会被实例化
    private LogCutQueryUtil() {
    }

    //获取唯一可用的对象
    public static LogCutQueryUtil getInstance() {
        return instance;
    }

    public LogCutQuery run() {
        LogCutQuery logCutQuery = new LogCutQuery();
        return logCutQuery;
    }
}
