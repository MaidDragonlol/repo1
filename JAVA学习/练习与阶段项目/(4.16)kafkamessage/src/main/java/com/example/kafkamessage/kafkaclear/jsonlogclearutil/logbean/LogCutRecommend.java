package com.example.kafkamessage.kafkaclear.jsonlogclearutil.logbean;

/*这个是记录JintongController.recommend(..)接口的标准类*/
public class LogCutRecommend {
    private static final long serialVersionUID = -6957361951748382519L;
    private String time;
    private String method;
    private String elapseMills;
    private String depAirport;
    private String arrAirport;
    private String msg;
    private String ota;

    public LogCutRecommend(String time, String method, String elapseMills, String depAirport, String arrAirport, String msg, String ota) {
        this.time = time;
        this.method = method;
        this.elapseMills = elapseMills;
        this.depAirport = depAirport;
        this.arrAirport = arrAirport;
        this.msg = msg;
        this.ota = ota;
    }

    public LogCutRecommend() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(String depAirport) {
        this.depAirport = depAirport;
    }

    public String getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(String arrAirport) {
        this.arrAirport = arrAirport;
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
        return "LogCutRecommend{" +
                "time='" + time + '\'' +
                ", method='" + method + '\'' +
                ", elapseMills='" + elapseMills + '\'' +
                ", depAirport='" + depAirport + '\'' +
                ", arrAirport='" + arrAirport + '\'' +
                ", msg='" + msg + '\'' +
                ", ota='" + ota + '\'' +
                '}';
    }
}
