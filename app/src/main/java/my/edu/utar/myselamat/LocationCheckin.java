package my.edu.utar.myselamat;

public class LocationCheckin {
    public String checklocation, checkdate, checktime;

    public LocationCheckin() {
    }

    public LocationCheckin(String checklocation, String checkdate, String checktime) {
        this.checklocation = checklocation;
        this.checkdate = checkdate;
        this.checktime = checktime;
    }

    public String getChecklocation() {
        return checklocation;
    }

    public void setChecklocation(String checklocation) {
        this.checklocation = checklocation;
    }

    public String getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(String checkdate) {
        this.checkdate = checkdate;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }
}
