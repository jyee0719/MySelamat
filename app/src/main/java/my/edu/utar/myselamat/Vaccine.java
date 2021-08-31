package my.edu.utar.myselamat;

public class Vaccine {
    private String vtype, vimage, vinfo;

    public Vaccine() {
    }

    public Vaccine(String vtype, String vimage, String vinfo) {
        this.vtype = vtype;
        this.vimage = vimage;
        this.vinfo = vinfo;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getVimage() {
        return vimage;
    }

    public void setVimage(String vimage) {
        this.vimage = vimage;
    }

    public String getVinfo() {
        return vinfo;
    }

    public void setVinfo(String vinfo) {
        this.vinfo = vinfo;
    }
}
