package com.example.kafkamessage.kafkaclear.kafka;
import org.bson.Document;

public class Fish {

    private String time;
    private String msg;
    private String route;
    private String method;
    private String ota;
    public Fish() {
    }

    public Fish(String time, String msg, String route, String method, String ota) {

        this.time = time;
        this.msg = msg;
        this.route = route;
        this.method = method;
        this.ota = ota;
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
                .append("msg", getMsg())
                .append("route", getRoute())
                .append("method", getMethod())
                .append("ota", getOta());
        return fishDocument;
    }

    @Override
    public String toString() {
        return "Fish{" +
                ", time='" + time + '\'' +
                ", msg='" + msg + '\'' +
                ", route='" + route + '\'' +
                ", method='" + method + '\'' +
                ", ota='" + ota + '\'' +
                '}';
    }
}