package Been;

public class args {
    private String cid;
    private Integer tripType;
    private String fromCity;
    private String toCity;
    private Integer fromDate;
    private Integer retDate;
    private Integer cabinClass;
    private Integer adultNumber;
    private Integer childNumber;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getTripType() {
        return tripType;
    }

    public void setTripType(Integer tripType) {
        this.tripType = tripType;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public Integer getFromDate() {
        return fromDate;
    }

    public void setFromDate(Integer fromDate) {
        this.fromDate = fromDate;
    }

    public Integer getRetDate() {
        return retDate;
    }

    public void setRetDate(Integer retDate) {
        this.retDate = retDate;
    }

    public Integer getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(Integer cabinClass) {
        this.cabinClass = cabinClass;
    }

    public Integer getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(Integer adultNumber) {
        this.adultNumber = adultNumber;
    }

    public Integer getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(Integer childNumber) {
        this.childNumber = childNumber;
    }

    @Override
    public String toString() {
        return "args{" +
                "cid='" + cid + '\'' +
                ", tripType=" + tripType +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", fromDate=" + fromDate +
                ", retDate=" + retDate +
                ", cabinClass=" + cabinClass +
                ", adultNumber=" + adultNumber +
                ", childNumber=" + childNumber +
                '}';
    }
}
