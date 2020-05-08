package logclearutil;

import logbean.LogCutQuery;
import logbean.LogCutRecommend;
/*把获得的属性值存入相应的标准类*/
public class InsertToBeanUtil {
    //创建 SingleObject 的一个对象
    private static InsertToBeanUtil instance = new InsertToBeanUtil();

    //让构造函数为 private，这样该类就不会被实例化
    private InsertToBeanUtil(){}

    //获取唯一可用的对象
    public static InsertToBeanUtil getInstance(){
        return instance;
    }

    public void runLogCutQuery(LogCutQuery logCutQuery,String[] everyMap){
        String node = everyMap[0];
        String everyContent = everyMap[1];
            switch (node) {
                case "time": {
                    logCutQuery.setTime(everyContent);
                }
                break;
                case "method": {
                    logCutQuery.setMethod(everyContent);
                }
                break;
                case "elapseMills": {
                    logCutQuery.setElapseMills(everyContent);
                }
                break;
                case "fromCity": {
                    logCutQuery.setFromCity(everyContent);
                }
                break;
                case "toCity": {
                    logCutQuery.setToCity(everyContent);
                }
                break;
                case "msg": {
                    logCutQuery.setMsg(everyContent);
                }
                break;
                case "ota": {
                    logCutQuery.setOta(everyContent);
                }
                break;
            }
        }
    public void runLogCutRecommend(LogCutRecommend logCutRecommend,String[] everyMap){
        String node = everyMap[0];
        String everyContent = everyMap[1];
            switch (node) {
                case "time": {
                    logCutRecommend.setTime(everyContent);
                }
                break;
                case "method": {
                    logCutRecommend.setMethod(everyContent);
                }
                break;
                case "elapseMills": {
                    logCutRecommend.setElapseMills(everyContent);
                }
                break;
                case "msg": {
                    logCutRecommend.setMsg(everyContent);
                }
                break;
                case "ota": {
                    logCutRecommend.setOta(everyContent);
                }
                break;
                case "depAirport": {
                    logCutRecommend.setDepAirport(everyContent);
                }
                break;
                case "stopCities": {
                    logCutRecommend.setStopCities(everyContent);
                }
                break;
            }
        }

    }

