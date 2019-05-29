package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationResponse {

    @SerializedName("destination_lat")
    @Expose
    String destination_lat;
    @SerializedName("destination_long")
    @Expose
    String destination_long;


    public String getlatitude() {
        return destination_lat;
    }
    public String getlongitude() {
        return destination_long;
    }
}
