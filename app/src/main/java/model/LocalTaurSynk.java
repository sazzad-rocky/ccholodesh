package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class LocalTaurSynk {

    @SerializedName("local_trip_plan_customer_id")
    @Expose
    private String customerId;

    @SerializedName("local_trip_plan_depart_district_id")
    @Expose
    private int deapartDistrictId;
    @SerializedName("local_trip_plan_destination_district_id")
    @Expose
    private int destinationDistrictId;
    @SerializedName("local_trip_plan_depart_district_name")
    @Expose
    private String departDistrictName;
    @SerializedName("destinationDistrictName")
    @Expose
    private String destinationDistrictName;
    @SerializedName("local_trip_plan_depart_date")
    @Expose
    private String departDate;


    @SerializedName("local_trip_plan_number_of_tourists")
    @Expose
    private  int numberOFTourists;

    @SerializedName("local_trip_plan_itineraries")
    @Expose
    private List<Itenerary> iteneraries=new ArrayList<>();

    @SerializedName("local_trip_plan_total_cost")
    @Expose
    private String totalCost;

    @SerializedName("request_type")
    @Expose
    public  String requestType;

    @SerializedName("request_version")
    @Expose
    public  String requestVersion;


    @SerializedName("tailormade_tailormade_id")
    @Expose
    public  int tailorMadeTailorMadeId;

    //start sazzad


    //   private String check_in_date;


    //  private String ;

//    public TailormadeSync setTailormadeNumberOfChild(List<String> tailormadeNumberOfChild ) {
//
//        this.tailormadeNumberOfChild = tailormadeNumberOfChild;
//        return this;
//
//    }
//
//
//    public TailormadeSync settailormadeChildDescription( List<String> tailormadeChildDescription ) {
//        this.tailormadeChildDescription = tailormadeChildDescription;
//        return this;
//
//    }





    public void setTotalCost (String totalCost)
    {
        this.totalCost = totalCost;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalTaurSynk setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }


    public List<Itenerary> getIteneraries() {
        return iteneraries;
    }

    public LocalTaurSynk setIteneraries(List<Itenerary> iteneraries) {
        this.iteneraries = iteneraries;
        return this;
    }



    public LocalTaurSynk setDeapartDistrictId(int deapartDistrictId) {
        this.deapartDistrictId = deapartDistrictId;
        return this;
    }

    public LocalTaurSynk setDestinationDistrictId(int destinationDistrictId) {
        this.destinationDistrictId = destinationDistrictId;
        return this;
    }

    public LocalTaurSynk setDepartDistrictName(String departDistrictName) {
        this.departDistrictName = departDistrictName;
        return this;
    }

    public LocalTaurSynk setDestinationDistrictName(String destinationDistrictName) {
        this.destinationDistrictName = destinationDistrictName;
        return this;
    }

    public LocalTaurSynk setDepartDate(String departDate) {
        this.departDate = departDate;
        return this;
    }





    public LocalTaurSynk setNumberOFTourists(int numberOFTourists) {
        this.numberOFTourists = numberOFTourists;
        return this;
    }





    public int getDeapartDistrictId() {
        return deapartDistrictId;
    }

    public int getDestinationDistrictId() {
        return destinationDistrictId;
    }

    public String getDepartDistrictName() {
        return departDistrictName;
    }

    public String getDestinationDistrictName() {
        return destinationDistrictName;
    }

    public String getDepartDate() {
        return departDate;
    }





    public int getNumberOFTourists() {
        return numberOFTourists;
    }


}
