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

public class NearByPlaceTypeInfo {

    @SerializedName("destination_nearby_place_type_id")
    @Expose
    private Integer destinationNearbyPlaceTypeId;
    @SerializedName("place_type_id")
    @Expose
    private String placeTypeId;
    @SerializedName("destination_nearby_place_id")
    @Expose
    private String destinationNearbyPlaceId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("place_type_name")
    @Expose
    private String placeTypeName;
    @SerializedName("place_type_name_bn")
    @Expose
    private String placeTypeNameBn;
    @SerializedName("place_type_status")
    @Expose
    private String placeTypeStatus;

    public Integer getDestinationNearbyPlaceTypeId() {
        return destinationNearbyPlaceTypeId;
    }

    public void setDestinationNearbyPlaceTypeId(Integer destinationNearbyPlaceTypeId) {
        this.destinationNearbyPlaceTypeId = destinationNearbyPlaceTypeId;
    }

    public String getPlaceTypeId() {
        return placeTypeId;
    }

    public void setPlaceTypeId(String placeTypeId) {
        this.placeTypeId = placeTypeId;
    }

    public String getDestinationNearbyPlaceId() {
        return destinationNearbyPlaceId;
    }

    public void setDestinationNearbyPlaceId(String destinationNearbyPlaceId) {
        this.destinationNearbyPlaceId = destinationNearbyPlaceId;
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

    public String getPlaceTypeName() {
        return placeTypeName;
    }

    public void setPlaceTypeName(String placeTypeName) {
        this.placeTypeName = placeTypeName;
    }

    public String getPlaceTypeNameBn() {
        return placeTypeNameBn;
    }

    public void setPlaceTypeNameBn(String placeTypeNameBn) {
        this.placeTypeNameBn = placeTypeNameBn;
    }

    public String getPlaceTypeStatus() {
        return placeTypeStatus;
    }

    public void setPlaceTypeStatus(String placeTypeStatus) {
        this.placeTypeStatus = placeTypeStatus;
    }


}
