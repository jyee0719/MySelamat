package my.edu.utar.myselamat;

//store location details
public class LocationCheckin {
    public String checkinlocation, checkindate, checkintime;

    public LocationCheckin() {
    }

    public LocationCheckin(String checkinlocation, String checkindate, String checkintime) {
        this.checkinlocation = checkinlocation;
        this.checkindate = checkindate;
        this.checkintime = checkintime;
    }

    public String getCheckinlocation() {
        return checkinlocation;
    }

    public void setCheckinlocation(String checkinlocation) {
        this.checkinlocation = checkinlocation;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(String checkintime) {
        this.checkintime = checkintime;
    }
}
