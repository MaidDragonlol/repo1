package logbeen;

public class Message {
    private String method;
    private String elapseMills;
    private Args args;

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

    public Args getArgs() {
        return args;
    }

    public void setArgs(Args args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Message{" +
                "method='" + method + '\'' +
                ", elapseMills='" + elapseMills + '\'' +
                ", args=" + args +
                '}';
    }
}
