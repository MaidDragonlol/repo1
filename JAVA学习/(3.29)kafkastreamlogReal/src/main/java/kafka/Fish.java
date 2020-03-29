package kafka;
import org.bson.Document;

public class Fish {
    private String _id;
    private String time;
    private String msg;
    private String route;
    private String method;
    private String ota;
    public Fish() {
    }

    public Fish(String _id,String time, String msg, String route, String method, String ota) {
        this._id = _id;
        this.time = time;
        this.msg = msg;
        this.route = route;
        this.method = method;
        this.ota = ota;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOta() {
        return ota;
    }

    public void setOta(String ota) {
        this.ota = ota;
    }

    public Document getFishAsDocument() {
        Document fishDocument = new Document("time", getTime())
                .append("_id", get_id())
                .append("msg", getMsg())
                .append("route", getRoute())
                .append("method", getMethod())
                .append("ota", getOta());
        return fishDocument;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "_id='" + _id + '\'' +
                ", time='" + time + '\'' +
                ", msg='" + msg + '\'' +
                ", route='" + route + '\'' +
                ", method='" + method + '\'' +
                ", ota='" + ota + '\'' +
                '}';
    }
}