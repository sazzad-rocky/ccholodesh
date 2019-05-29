/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

/**
 * Created by rhythmshahriar on 7/19/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearByPlacesInfo {

    @SerializedName("destination_nearby_place_id")
    @Expose
    private Integer destinationNearbyPlaceId;
    @SerializedName("destination_nearby_place_destination_id")
    @Expose
    private String destinationNearbyPlaceDestinationId;
    @SerializedName("destination_nearby_place_name")
    @Expose
    private String destinationNearbyPlaceName;
    @SerializedName("destination_nearby_place_name_bn")
    @Expose
    private String destinationNearbyPlaceNameBn;
    @SerializedName("destination_nearby_place_details_bn")
    @Expose
    private String destinationNearbyPlaceDetailsBn;
    @SerializedName("destination_nearby_place_tag_bn")
    @Expose
    private String destinationNearbyPlaceTagBn;
    @SerializedName("destination_nearby_place_details")
    @Expose
    private String destinationNearbyPlaceDetails;
    @SerializedName("destination_nearby_place_image")
    @Expose
    private String destinationNearbyPlaceImage;
    @SerializedName("destination_nearby_place_lat")
    @Expose
    private String destinationNearbyPlaceLat;
    @SerializedName("destination_nearby_place_long")
    @Expose
    private String destinationNearbyPlaceLong;
    @SerializedName("destination_nearby_place_tag")
    @Expose
    private String destinationNearbyPlaceTag;
    @SerializedName("destination_nearby_place_type")
    @Expose
    private String destinationNearbyPlaceType;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getDestinationNearbyPlaceId() {
        return destinationNearbyPlaceId;
    }

    public void setDestinationNearbyPlaceId(Integer destinationNearbyPlaceId) {
        this.destinationNearbyPlaceId = destinationNearbyPlaceId;
    }

    public String getDestinationNearbyPlaceDestinationId() {
        return destinationNearbyPlaceDestinationId;
    }

    public void setDestinationNearbyPlaceDestinationId(String destinationNearbyPlaceDestinationId) {
        this.destinationNearbyPlaceDestinationId = destinationNearbyPlaceDestinationId;
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

    public String getDestinationNearbyPlaceDetailsBn() {
        return destinationNearbyPlaceDetailsBn;
    }

    public void setDestinationNearbyPlaceDetailsBn(String destinationNearbyPlaceDetailsBn) {
        this.destinationNearbyPlaceDetailsBn = destinationNearbyPlaceDetailsBn;
    }

    public String getDestinationNearbyPlaceTagBn() {
        return destinationNearbyPlaceTagBn;
    }

    public void setDestinationNearbyPlaceTagBn(String destinationNearbyPlaceTagBn) {
        this.destinationNearbyPlaceTagBn = destinationNearbyPlaceTagBn;
    }

    public String getDestinationNearbyPlaceDetails() {
        return destinationNearbyPlaceDetails;
    }

    public void setDestinationNearbyPlaceDetails(String destinationNearbyPlaceDetails) {
        this.destinationNearbyPlaceDetails = destinationNearbyPlaceDetails;
    }

    public String getDestinationNearbyPlaceImage() {
        return destinationNearbyPlaceImage;
    }

    public void setDestinationNearbyPlaceImage(String destinationNearbyPlaceImage) {
        this.destinationNearbyPlaceImage = destinationNearbyPlaceImage;
    }

    public String getDestinationNearbyPlaceLat() {
        return destinationNearbyPlaceLat;
    }

    public void setDestinationNearbyPlaceLat(String destinationNearbyPlaceLat) {
        this.destinationNearbyPlaceLat = destinationNearbyPlaceLat;
    }

    public String getDestinationNearbyPlaceLong() {
        return destinationNearbyPlaceLong;
    }

    public void setDestinationNearbyPlaceLong(String destinationNearbyPlaceLong) {
        this.destinationNearbyPlaceLong = destinationNearbyPlaceLong;
    }

    public String getDestinationNearbyPlaceTag() {
        return destinationNearbyPlaceTag;
    }

    public void setDestinationNearbyPlaceTag(String destinationNearbyPlaceTag) {
        this.destinationNearbyPlaceTag = destinationNearbyPlaceTag;
    }

    public String getDestinationNearbyPlaceType() {
        return destinationNearbyPlaceType;
    }

    public void setDestinationNearbyPlaceType(String destinationNearbyPlaceType) {
        this.destinationNearbyPlaceType = destinationNearbyPlaceType;
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

    @Override
    public String toString() {
        return destinationNearbyPlaceName;
    }
}
