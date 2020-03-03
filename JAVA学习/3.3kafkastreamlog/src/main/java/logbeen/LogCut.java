package logbeen;

public class LogCut {
    private String time;
    private Message message;

    public String getTime() {
        return time;
    }

    public  void setTime(String time) {
        this.time = time;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogCut{" +
                "time='" + time + '\'' +
                ", message=" + message +
                '}';
    }
}
