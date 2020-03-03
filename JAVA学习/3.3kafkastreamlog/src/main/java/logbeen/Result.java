package logbeen;

public class Result {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
