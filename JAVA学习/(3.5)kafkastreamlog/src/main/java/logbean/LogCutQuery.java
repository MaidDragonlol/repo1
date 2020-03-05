package logbean;

import java.io.Serializable;
/*这个是记录TuniuController.query(..)接口的标准类*/
public class LogCutQuery implements Serializable {
    private static final long serialVersionUID = -6957361951748382519L;
    private String time;
    private String method;
    private String elapseMills;
    private String fromCity;
    private String toCity;
    private String msg;
    private String ota;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getElapseMills() {
        return elapseMills;
    }

    public void setElapseMills(String elapseMills) {
        this.elapseMills = elapseMills;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOta() {
        return ota;
    }

    public void setOta(String ota) {
        this.ota = ota;
    }

    @Override
    public String toString() {
        return "LogCutQuery{" +
                "time='" + time + '\'' +
                ", method='" + method + '\'' +
                ", elapseMills='" + elapseMills + '\'' +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", msg='" + msg + '\'' +
                ", ota='" + ota + '\'' +
                '}';
    }

}
