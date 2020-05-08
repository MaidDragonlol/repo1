package Been;

public class message {
    private String method;
    private String startTime;
    private Integer startMills;
    private Integer elapseMills;
    private args args;
    private result result;
    private String error;
    private String exMsg;
    private String ota;
    private String stage;
    private String path;
    private String remoteAddr;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getStartMills() {
        return startMills;
    }

    public void setStartMills(Integer startMills) {
        this.startMills = startMills;
    }

    public Integer getElapseMills() {
        return elapseMills;
    }

    public void setElapseMills(Integer elapseMills) {
        this.elapseMills = elapseMills;
    }

    public Been.args getArgs() {
        return args;
    }

    public void setArgs(Been.args args) {
        this.args = args;
    }

    public Been.result getResult() {
        return result;
    }

    public void setResult(Been.result result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }

    public String getOta() {
        return ota;
    }

    public void setOta(String ota) {
        this.ota = ota;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    @Override
    public String toString() {
        return "message{" +
                "method='" + method + '\'' +
                ", startTime='" + startTime + '\'' +
                ", startMills=" + startMills +
                ", elapseMills=" + elapseMills +
                ", args=" + args +
                ", result=" + result +
                ", error='" + error + '\'' +
                ", exMsg='" + exMsg + '\'' +
                ", ota='" + ota + '\'' +
                ", stage='" + stage + '\'' +
                ", path='" + path + '\'' +
                ", remoteAddr='" + remoteAddr + '\'' +
                '}';
    }
}
