package my.edu.utar.myselamat;

public class Place {
    private String addr, lat, longti,cases,risk;

    public Place(String addr, String lat, String longti, String cases, String risk) {
        this.addr = addr;
        this.lat = lat;
        this.longti = longti;
        this.cases = cases;
        this.risk = risk;
    }

    public Place() {

    }

    public String getAddr() {
        return addr;
    }

    public String getLat() {
        return lat;
    }

    public String getLongti() {
        return longti;
    }

    public String getCases() {
        return cases;
    }

    public String getRisk() {
        return risk;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLongti(String longti) {
        this.longti = longti;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
