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

/**
 * Created by rhythmshahriar on 7/18/17.
 */

public class NearByPlaces {

    @SerializedName("destination_nearby_place_id")
    @Expose
    private Integer destinationNearbyPlaceId;
    @SerializedName("destination_nearby_place_name")
    @Expose
    private String destinationNearbyPlaceName;
    @SerializedName("destination_nearby_place_name_bn")
    @Expose
    private String destinationNearbyPlaceNameBn;
    @SerializedName("destination_nearby_place_image")
    @Expose
    private String destinationNearbyPlaceImage;

    public Integer getDestinationNearbyPlaceId() {
        return destinationNearbyPlaceId;
    }

    public void setDestinationNearbyPlaceId(Integer destinationNearbyPlaceId) {
        this.destinationNearbyPlaceId = destinationNearbyPlaceId;
    }

    public String getDestinationNearbyPlaceName() {
        return destinationNearbyPlaceName;
    }

    public void setDestinationNearbyPlaceName(String destinationNearbyPlaceName) {
        this.destinationNearbyPlaceName = destinationNearbyPlaceName;
    }

    public String getDestinationNearbyPlaceNameBn() {
        return destinationNearbyPlaceNameBn;
    }

    public void setDestinationNearbyPlaceNameBn(String destinationNearbyPlaceNameBn) {
        this.destinationNearbyPlaceNameBn = destinationNearbyPlaceNameBn;
    }

    public String getDestinationNearbyPlaceImage() {
        return destinationNearbyPlaceImage;
    }

    public void setDestinationNearbyPlaceImage(String destinationNearbyPlaceImage) {
        this.destinationNearbyPlaceImage = destinationNearbyPlaceImage;
    }

}
