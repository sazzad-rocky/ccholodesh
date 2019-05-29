/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

/**
 * Created by rhythmshahriar on 9/14/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TailorMadeList {

    @SerializedName("tailormade_id")
    @Expose
    private Integer tailormadeId;
    @SerializedName("tailormade_customer_id")
    @Expose
    private String tailormadeCustomerId;

    @SerializedName("tailormade_app_tailormade_id")
    @Expose
    private String tailormadeAppTailormadeId;
    @SerializedName("tailormade_depart_district_id")
    @Expose
    private String tailormadeDepartDistrictId;
    @SerializedName("tailormade_depart_district_name")
    @Expose
    private String tailormadeDepartDistrictName;
    @SerializedName("tailormade_destination_district_id")
    @Expose
    private String tailormadeDestinationDistrictId;
    @SerializedName("tailormade_destination_district_name")
    @Expose
    private String tailormadeDestinationDistrictName;
    @SerializedName("tailormade_destination_district_image")
    @Expose
    private String tailormadeDestinationDistrictImage;
    @SerializedName("tailormade_total_cost")
    @Expose
    private String tailormadeTotalCost;
    @SerializedName("tailormade_from_date")
    @Expose
    private String tailormadeFromDate;
    @SerializedName("tailormade_to_date")
    @Expose
    private String tailormadeToDate;
    @SerializedName("tailormade_days")
    @Expose
    private String tailormadeDays;
    @SerializedName("tailormade_person")
    @Expose
    private String tailormadePerson;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("tailormade_status")
    @Expose
    public String status;

    @SerializedName("provider_name")
    @Expose
    public String providerName;
    @SerializedName("trip_bidding_details_amount")
    @Expose
    public String biddingAmount;
    @SerializedName("trip_bidding_details_confirmation_date")
    @Expose
    public String confirmationDate;

    @SerializedName("provider_image")
    @Expose
    public String providerImage;

    @SerializedName("tailormade_number_of_child")
    @Expose
    public String noOfChildren;

    @SerializedName("tailormade_child_description")
    @Expose
    public String childrenDesc;

    //start sazzad
    @SerializedName("tailormade_confirm_status")
    @Expose
    private String tailormade_confirmStatus;

    @SerializedName("tailormade_confirm_message")
    @Expose
    private String tailormade_confirmMessage;
    //start


    @SerializedName("check_in_date")
    @Expose
    public String check_in_date;

    @SerializedName("check_out_date")
    @Expose
    public String check_out_date;

    @SerializedName("district_lat")
    @Expose
    public String district_lat;

    @SerializedName("district_long")
    @Expose
    public String district_long;



    public String getlatitude() {
        return district_lat;
    }

    public String getlongitude() {
        return district_long;
    }



    public String getcheck_in_date() {
        return check_in_date;
    }

    public String getcheck_out_date() {
        return check_out_date;
    }
    //end

    public String gettailormade_confirm_status() {
        return tailormade_confirmStatus;
    }

    public String gettailormade_confirm_message() {
        return tailormade_confirmMessage;
    }

    //end

    public Integer getTailormadeId() {
        return tailormadeId;
    }

    public void setTailormadeId(Integer tailormadeId) {
        this.tailormadeId = tailormadeId;
    }

    public String getTailormadeCustomerId() {
        return tailormadeCustomerId;
    }

    public void setTailormadeCustomerId(String tailormadeCustomerId) {
        this.tailormadeCustomerId = tailormadeCustomerId;
    }

    public String getTailormadeAppTailormadeId() {
        return tailormadeAppTailormadeId;
    }

    public void setTailormadeAppTailormadeId(String tailormadeAppTailormadeId) {
        this.tailormadeAppTailormadeId = tailormadeAppTailormadeId;
    }

    public String getTailormadeDepartDistrictId() {
        return tailormadeDepartDistrictId;
    }

    public void setTailormadeDepartDistrictId(String tailormadeDepartDistrictId) {
        this.tailormadeDepartDistrictId = tailormadeDepartDistrictId;
    }

    public String getTailormadeDepartDistrictName() {
        return tailormadeDepartDistrictName;
    }

    public void setTailormadeDepartDistrictName(String tailormadeDepartDistrictName) {
        this.tailormadeDepartDistrictName = tailormadeDepartDistrictName;
    }

    public String getTailormadeDestinationDistrictId() {
        return tailormadeDestinationDistrictId;
    }

    public void setTailormadeDestinationDistrictId(String tailormadeDestinationDistrictId) {
        this.tailormadeDestinationDistrictId = tailormadeDestinationDistrictId;
    }

    public String getTailormadeDestinationDistrictName() {
        return tailormadeDestinationDistrictName;
    }

    public void setTailormadeDestinationDistrictName(String tailormadeDestinationDistrictName) {
        this.tailormadeDestinationDistrictName = tailormadeDestinationDistrictName;
    }

    public String getTailormadeDestinationDistrictImage() {
        return tailormadeDestinationDistrictImage;
    }

    public void setTailormadeDestinationDistrictImage(String tailormadeDestinationDistrictImage) {
        this.tailormadeDestinationDistrictImage = tailormadeDestinationDistrictImage;
    }

    public String getTailormadeTotalCost() {
        return tailormadeTotalCost;
    }

    public void setTailormadeTotalCost(String tailormadeTotalCost) {
        this.tailormadeTotalCost = tailormadeTotalCost;
    }

    public String getTailormadeFromDate() {
        return tailormadeFromDate;
    }

    public void setTailormadeFromDate(String tailormadeFromDate) {
        this.tailormadeFromDate = tailormadeFromDate;
    }

    public String getTailormadeToDate() {
        return tailormadeToDate;
    }

    public void setTailormadeToDate(String tailormadeToDate) {
        this.tailormadeToDate = tailormadeToDate;
    }

    public String getTailormadeDays() {
        return tailormadeDays;
    }

    public void setTailormadeDays(String tailormadeDays) {
        this.tailormadeDays = tailormadeDays;
    }

    public String getTailormadePerson() {
        return tailormadePerson;
    }

    public void setTailormadePerson(String tailormadePerson) {
        this.tailormadePerson = tailormadePerson;
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