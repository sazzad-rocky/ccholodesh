package model;

public class ItineryTailormade {
    String Providername, Date;

    public ItineryTailormade(String providername, String date) {
        Providername = providername;
        Date = date;
    }

    public ItineryTailormade() {
    }

    public String getProvidername() {
        return Providername;
    }

    public void setProvidername(String providername) {
        Providername = providername;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
