package Been;

public class LogBeen {
    private String time;
    private String thread;
    private String categoryId;
    private String level;
    private message message;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Been.message getMessage() {
        return message;
    }

    public void setMessage(Been.message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogBeen{" +
                "time='" + time + '\'' +
                ", thread='" + thread + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", level='" + level + '\'' +
                ", message=" + message +
                '}';
    }
}
