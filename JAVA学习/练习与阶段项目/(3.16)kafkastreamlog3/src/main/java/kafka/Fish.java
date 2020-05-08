package kafka;
import org.bson.Document;
      /*  存储进入mongo数据库的标准类*/
public class Fish {
    private String route;
    private String routeTotalTimes;
    private String routeErrorTimes;

    public Fish(String route, String routeTotalTimes,String routeErrorTimes) {
        this.route = route;
        this.routeTotalTimes = routeTotalTimes;
        this.routeErrorTimes = routeErrorTimes;

    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRouteTotalTimes() {
        return routeTotalTimes;
    }

    public void setRouteTotalTimes(String routeTotalTimes) {
        this.routeTotalTimes = routeTotalTimes;
    }

    public String getRouteErrorTimes() {
        return routeErrorTimes;
    }

    public void setRouteErrorTimes(String routeErrorTimes) {
        this.routeErrorTimes = routeErrorTimes;
    }

    public Document getFishAsDocument() {
        Document fishDocument = new Document("route", getRoute())
                .append("routeTotalTimes", getRouteTotalTimes())
                .append("routeTotalTimes", getRouteErrorTimes());
        return fishDocument;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "route='" + route + '\'' +
                ", routeTotalTimes='" + routeTotalTimes + '\'' +
                ", routeErrorTimes='" + routeErrorTimes + '\'' +
                '}';
    }
}