package logbean;
/*这个是记录JintongController.recommend(..)接口的标准类*/
public class LogCutRecommend {
    private static final long serialVersionUID = -6957361951748382519L;
    private String time;
    private String method;
    private String elapseMills;
    private String depAirport;
    private String stopCities;
    private String msg;
    private String ota;

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

    public String getStopCities() {
        return stopCities;
    }

    public void setStopCities(String stopCities) {
        this.stopCities = stopCities;
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
                ", stopCities='" + stopCities + '\'' +
                ", msg='" + msg + '\'' +
                ", ota='" + ota + '\'' +
                '}';
    }
}
