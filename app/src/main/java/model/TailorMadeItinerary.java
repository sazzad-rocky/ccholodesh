/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

/**
 * Created by rhythmshahriar on 9/13/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TailorMadeItinerary {

    @SerializedName("itinerary_id")
    @Expose
    private Integer itineraryId;
    @SerializedName("itinerary_tailormade_id")
    @Expose
    private String itineraryTailormadeId;
    @SerializedName("itinerary_local_tour_id")
    @Expose
    private String itineraryLocalTourId;
    @SerializedName("itinerary_dayplan")
    @Expose
    private String itineraryDayplan;
    @SerializedName("itinerary_placeName")
    @Expose
    private String itineraryPlaceName;
    @SerializedName("itinerary_perPersonCost")
    @Expose
    private String itineraryPerPersonCost;
    @SerializedName("itinerary_time")
    @Expose
    private String itineraryTime;
    @SerializedName("itinerary_transport")
    @Expose
    private Object itineraryTransport;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("destination_nearby_place_lat")
    @Expose
    public String lattitude;

    @SerializedName("destination_nearby_place_long")
    @Expose
    public String longiTude;

    @SerializedName("local_tour_duration")
    @Expose
    public String localTourDuration;

    @SerializedName("destination_nearby_place_tag")
    @Expose
    public String localTourTags;






    public Integer getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Integer itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getItineraryTailormadeId() {
        return itineraryTailormadeId;
    }

    public void setItineraryTailormadeId(String itineraryTailormadeId) {
        this.itineraryTailormadeId = itineraryTailormadeId;
    }

    public String getItineraryLocalTourId() {
        return itineraryLocalTourId;
    }

    public void setItineraryLocalTourId(String itineraryLocalTourId) {
        this.itineraryLocalTourId = itineraryLocalTourId;
    }

    public String getItineraryDayplan() {
        return itineraryDayplan;
    }

    public void setItineraryDayplan(String itineraryDayplan) {
        this.itineraryDayplan = itineraryDayplan;
    }

    public String getItineraryPlaceName() {
        return itineraryPlaceName;
    }

    public void setItineraryPlaceName(String itineraryPlaceName) {
        this.itineraryPlaceName = itineraryPlaceName;
    }

    public String getItineraryPerPersonCost() {
        return itineraryPerPersonCost;
    }

    public void setItineraryPerPersonCost(String itineraryPerPersonCost) {
        this.itineraryPerPersonCost = itineraryPerPersonCost;
    }

    public String getItineraryTime() {
        return itineraryTime;
    }

    public void setItineraryTime(String itineraryTime) {
        this.itineraryTime = itineraryTime;
    }

    public Object getItineraryTransport() {
        return itineraryTransport;
    }

    public void setItineraryTransport(Object itineraryTransport) {
        this.itineraryTransport = itineraryTransport;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}