package logbeen;

public class Args {
    private String fromCity;
    private Result result;
    private String ota;

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getOta() {
        return ota;
    }

    public void setOta(String ota) {
        this.ota = ota;
    }

    @Override
    public String toString() {
        return "Args{" +
                "fromCity='" + fromCity + '\'' +
                ", result=" + result +
                ", ota='" + ota + '\'' +
                '}';
    }
}
