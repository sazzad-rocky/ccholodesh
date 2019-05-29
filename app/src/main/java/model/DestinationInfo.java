/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import fragments.Emergency;

/**
 * Created by rhythmshahriar on 7/18/17.
 */

public class DestinationInfo {

    @SerializedName("destination")
    @Expose
    private List<DestinationNew> destination = null;
    @SerializedName("destGallery")
    @Expose
    private List<DestinationGallery> destGallery = null;
    @SerializedName("destNearbyPlace")
    @Expose
    private List<NearByPlaces> destNearbyPlace = null;
    @SerializedName("destAttraction")
    @Expose
    private List<DestinationAttraction> destAttraction = null;
    @SerializedName("destEmergency")
    @Expose
    private List<Emergency> destEmergency = null;

    public List<DestinationNew> getDestination() {
        return destination;
    }

    public void setDestination(List<DestinationNew> destination) {
        this.destination = destination;
    }

    public List<DestinationGallery> getDestGallery() {
        return destGallery;
    }

    public void setDestGallery(List<DestinationGallery> destGallery) {
        this.destGallery = destGallery;
    }

    public List<NearByPlaces> getDestNearbyPlace() {
        return destNearbyPlace;
    }

    public void setDestNearbyPlace(List<NearByPlaces> destNearbyPlace) {
        this.destNearbyPlace = destNearbyPlace;
    }

    public List<DestinationAttraction> getDestAttraction() {
        return destAttraction;
    }

    public void setDestAttraction(List<DestinationAttraction> destAttraction) {
        this.destAttraction = destAttraction;
    }

    public List<Emergency> getDestEmergency() {
        return destEmergency;
    }

    public void setDestEmergency(List<Emergency> destEmergency) {
        this.destEmergency = destEmergency;
    }





}
