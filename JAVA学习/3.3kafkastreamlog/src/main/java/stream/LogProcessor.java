package stream;


import kafkastreamlogs.JsonPaser;
import kafkastreamlogs.unZipToString;
import logbeen.LogCutClear;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;



public class LogProcessor implements Processor<byte[], byte[]> {

    private ProcessorContext context;

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public void process(byte[] key, byte[] value) {
        /*这里是保存两种日志格式的标准类*/
        LogCutClear logCutQuery = new LogCutClear();
        LogCutClear logCutRecommend = new LogCutClear();
       /* String jsonContent = value.toString();*/
        String jsonContent = unZipToString.unZipToString(value);
        /*String jsonContent = BinstrToStr(Binstr16ToBinstr(BoolToBinstr16(value)));*/
        /*String jsonContent = System.Text.Encoding.GetEncoding("UTF-8").GetString(value);*/
        /*每个输入读取的地方都让用一个try/catch*/
        /*先把mothod读出来，用于判断日志格式:case "TuniuController.query(..)和case "JintongController.recommend(..)"*/
        try {
            String mothod = JsonPaser.getNodeValue(jsonContent, "JSON.message.method");
            switch (mothod) {
                case "TuniuController.query(..)": {
                    String time = null;
                    try {
                        time = JsonPaser.getNodeValue(jsonContent, "JSON.time");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String method = null;
                    try {
                        method = JsonPaser.getNodeValue(jsonContent, "JSON.message.method");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String elapseMills = null;
                    try {
                        elapseMills = JsonPaser.getNodeValue(jsonContent, "JSON.message.elapseMills");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String fromCity = null;
                    try {
                        fromCity = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.[fromCity]");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String toCity = null;
                    try {
                        toCity = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.[toCity]");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String msg = null;
                    try {
                        msg = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.result.msg");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String ota = null;
                    try {
                        ota = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.ota");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    /*System.out.println(fromCity);*/
                    System.out.println("time\n" + time + "\nmethod\n" + method + "\nelapseMills\n" + elapseMills + "\nfromCity\n" + fromCity + "\ntoCity\n" + toCity + "\nmsg\n" + msg + "\nota\n" + ota);
                    /*这里把获取到的数据存入实体类*/
                    logCutQuery.setTime(time);
                    logCutQuery.setMethod(method);
                    logCutQuery.setElapseMills(elapseMills);
                    logCutQuery.setFromCity(fromCity);
                    logCutQuery.setToCity(toCity);
                    logCutQuery.setMsg(msg);
                    logCutQuery.setOta(ota);
                    String valueLog = logCutQuery.toString();
                    // 输出到下一个topic
                    context.forward("logProcessor".getBytes(), valueLog.getBytes());

                }
                break;
                case "JintongController.recommend(..)": {
                    String time = null;
                    try {
                        time = JsonPaser.getNodeValue(jsonContent, "JSON.time");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String method = null;
                    try {
                        method = JsonPaser.getNodeValue(jsonContent, "JSON.message.method");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String elapseMills = null;
                    try {
                        elapseMills = JsonPaser.getNodeValue(jsonContent, "JSON.message.elapseMills");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String fromCity = null;
                    try {
                        fromCity = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.[routing.depAirport]");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String toCity = null;
                    try {
                        toCity = JsonPaser.getNodeValue(jsonContent, "JSON.message.args.[routing.stopCities]");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String msg = null;
                    try {
                        msg = JsonPaser.getNodeValue(jsonContent, "JSON.message.result.msg");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String ota = null;
                    try {
                        ota = JsonPaser.getNodeValue(jsonContent, "JSON.message.ota");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("time\n" + time + "\nmethod\n" + method + "\nelapseMills\n" + elapseMills + "\nfromCity\n" + fromCity + "\ntoCity\n" + toCity + "\nmsg\n" + msg + "\nota\n" + ota);
                    /*这里把获取到的数据存入实体类*/
                    logCutRecommend.setTime(time);
                    logCutRecommend.setMethod(method);
                    logCutRecommend.setElapseMills(elapseMills);
                    logCutRecommend.setFromCity(fromCity);
                    logCutRecommend.setToCity(toCity);
                    logCutRecommend.setMsg(msg);
                    logCutRecommend.setOta(ota);
                    String valueLog = logCutRecommend.toString();
                    // 输出到下一个topic
                    context.forward("logProcessor".getBytes(), valueLog.getBytes());
                }
                break;
                default:
                    System.out.println("没有");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void punctuate(long timestamp) {

    }

    @Override
    public void close() {

    }

}

