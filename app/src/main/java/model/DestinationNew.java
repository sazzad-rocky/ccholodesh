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

public class DestinationNew {

    @SerializedName("destination_id")
    @Expose
    private Integer destinationId;
    @SerializedName("destination_division_id")
    @Expose
    private String destinationDivisionId;
    @SerializedName("destination_district_id")
    @Expose
    private String destinationDistrictId;
    @SerializedName("destination_name")
    @Expose
    private String destinationName;
    @SerializedName("destination_name_bn")
    @Expose
    private String destinationNameBn;
    @SerializedName("destination_details")
    @Expose
    private String destinationDetails;
    @SerializedName("destination_image")
    @Expose
    private String destinationImage;
    @SerializedName("destination_lat")
    @Expose
    private String destinationLat;
    @SerializedName("destination_long")
    @Expose
    private String destinationLong;
    @SerializedName("destination_tags")
    @Expose
    private String destinationTags;
    @SerializedName("destination_status")
    @Expose
    private String destinationStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("destination_details_bn")
    @Expose
    private String destinationDetailsBn;
    @SerializedName("destination_tags_bn")
    @Expose
    private String destinationTagsBn;
    @SerializedName("division_id")
    @Expose
    private String divisionId;
    @SerializedName("division_name")
    @Expose
    private String divisionName;
    @SerializedName("division_name_bn")
    @Expose
    private String divisionNameBn;
    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("district_name_bn")
    @Expose
    private String districtNameBn;

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationDivisionId() {
        return destinationDivisionId;
    }

    public void setDestinationDivisionId(String destinationDivisionId) {
        this.destinationDivisionId = destinationDivisionId;
    }

    public String getDestinationDistrictId() {
        return destinationDistrictId;
    }

    public void setDestinationDistrictId(String destinationDistrictId) {
        this.destinationDistrictId = destinationDistrictId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationNameBn() {
        return destinationNameBn;
    }

    public void setDestinationNameBn(String destinationNameBn) {
        this.destinationNameBn = destinationNameBn;
    }

    public String getDestinationDetails() {
        return destinationDetails;
    }

    public void setDestinationDetails(String destinationDetails) {
        this.destinationDetails = destinationDetails;
    }

    public String getDestinationImage() {
        return destinationImage;
    }

    public void setDestinationImage(String destinationImage) {
        this.destinationImage = destinationImage;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public String getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(String destinationLong) {
        this.destinationLong = destinationLong;
    }

    public String getDestinationTags() {
        return destinationTags;
    }

    public void setDestinationTags(String destinationTags) {
        this.destinationTags = destinationTags;
    }

    public String getDestinationStatus() {
        return destinationStatus;
    }

    public void setDestinationStatus(String destinationStatus) {
        this.destinationStatus = destinationStatus;
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

    public String getDestinationDetailsBn() {
        return destinationDetailsBn;
    }

    public void setDestinationDetailsBn(String destinationDetailsBn) {
        this.destinationDetailsBn = destinationDetailsBn;
    }

    public String getDestinationTagsBn() {
        return destinationTagsBn;
    }

    public void setDestinationTagsBn(String destinationTagsBn) {
        this.destinationTagsBn = destinationTagsBn;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionNameBn() {
        return divisionNameBn;
    }

    public void setDivisionNameBn(String divisionNameBn) {
        this.divisionNameBn = divisionNameBn;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictNameBn() {
        return districtNameBn;
    }

    public void setDistrictNameBn(String districtNameBn) {
        this.districtNameBn = districtNameBn;
    }

//    @Override
//    public String toString() {
//        return this.districtName;
//    }
}
