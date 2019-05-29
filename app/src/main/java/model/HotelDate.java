package model;

public class HotelDate {
    String hotelName;
    String checkindate;
    String checkoutDate;
    int providerdate;

    public HotelDate(String hotelName, String checkindate, String checkoutDate) {
        this.hotelName = hotelName;
        this.checkindate = checkindate;
        this.checkoutDate = checkoutDate;
    }

    public HotelDate(String hotelName, String checkindate, String checkoutDate, int providerdate) {
        this.hotelName = hotelName;
        this.checkindate = checkindate;
        this.checkoutDate = checkoutDate;
        this.providerdate = providerdate;
    }

    public int getProviderdate() {
        return providerdate;
    }

    public void setProviderdate(int providerdate) {
        this.providerdate = providerdate;
    }

    public HotelDate() {
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}
