package model;

public class DateStore {
    String checkin;
    String checkOut;

    public DateStore() {
    }

    public DateStore(String checkin, String checkOut) {
        this.checkin = checkin;
        this.checkOut = checkOut;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
