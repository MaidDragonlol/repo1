package Been;

public class result {
    private String status;
    private String msg;
    private String routings;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRoutings() {
        return routings;
    }

    public void setRoutings(String routings) {
        this.routings = routings;
    }

    @Override
    public String toString() {
        return "result{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", routings='" + routings + '\'' +
                '}';
    }
}
