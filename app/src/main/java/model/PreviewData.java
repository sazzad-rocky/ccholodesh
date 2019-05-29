package model;

import java.util.ArrayList;
import java.util.List;

public class PreviewData  {

    public static int item=0;
   public static String itinererycost="";
   public static String totalcost="";
   public static String fromPlace="";
   public static String fromRoute="";
   public static String toRoute="";
    public static    String toPlace="";
    public static  String torrists="";
    public static  String days="";
    public static String noOfchild="";
    public static String avgage="";
    public static String stratDate="";
    public static String returnDate="";
    public static String route="";

    public static  String checkindate="";
    public static  String checkoutDate="";
    public static  String transportname="";
    public static  String transportnametwo="";
    public static  String hotelName="";
    public static  String hotelNameTwo="";
    public static List<String> strings = new ArrayList<>();

    public static  String hotelNametwo="";
    public static  String transportcost="";
    public static String hotelcost="";

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getTorrists() {
        return torrists;
    }

    public void setTorrists(String torrists) {
        this.torrists = torrists;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getNoOfchild() {
        return noOfchild;
    }

    public void setNoOfchild(String noOfchild) {
        this.noOfchild = noOfchild;
    }

    public String getAvgage() {
        return avgage;
    }

    public void setAvgage(String avgage) {
        this.avgage = avgage;
    }

    public String getStratDate() {
        return stratDate;
    }

    public void setStratDate(String stratDate) {
        this.stratDate = stratDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
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
