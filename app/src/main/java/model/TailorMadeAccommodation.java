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

public class TailorMadeAccommodation {

    @SerializedName("tailormade_accommodation_id")
    @Expose
    private Integer tailormadeAccommodationId;
    @SerializedName("tailormade_accommodation_district_id")
    @Expose
    private String tailormadeAccommodationDistrictId;
    @SerializedName("tailormade_accommodation_district_name")
    @Expose
    private String tailormadeAccommodationDistrictName;
    @SerializedName("tailormade_accommodation_tailormade_id")
    @Expose
    private String tailormadeAccommodationTailormadeId;
    @SerializedName("tailormade_accommodation_accommodation_id")
    @Expose
    private String tailormadeAccommodationAccommodationId;
    @SerializedName("tailormade_accommodation_accommodation_name")
    @Expose
    private String tailormadeAccommodationAccommodationName;
    @SerializedName("tailormade_accommodation_room_id")
    @Expose
    private String tailormadeAccommodationRoomId;
    @SerializedName("tailormade_accommodation_room_type_name")
    @Expose
    private Object tailormadeAccommodationRoomTypeName;
    @SerializedName("tailormade_accommodation_accommodation_room_max_occupancy")
    @Expose
    private String tailormadeAccommodationAccommodationRoomMaxOccupancy;
    @SerializedName("tailormade_accommodation_accommodation_room_price")
    @Expose
    private String tailormadeAccommodationAccommodationRoomPrice;
    @SerializedName("tailormade_accommodation_accommodation_category_id")
    @Expose
    private String tailormadeAccommodationAccommodationCategoryId;
    @SerializedName("tailormade_accommodation_accommodation_category_name")
    @Expose
    private String tailormadeAccommodationAccommodationCategoryName;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("tailormade_accommodation_room_image")
    @Expose
    private String image;


    public String getImage () {return this.image;}

    @SerializedName("check_in_date")
    @Expose
    private String check_in_date;

    @SerializedName("check_out_date")
    @Expose
    private String check_out_date;

    public String getTailormadeAccommodationCheckindate() {
        return check_in_date;
    }
    public String getTailormadeAccommodationCheckOutdate() {
        return check_out_date;
    }

    public Integer getTailormadeAccommodationId() {
        return tailormadeAccommodationId;
    }

    public void setTailormadeAccommodationId(Integer tailormadeAccommodationId) {
        this.tailormadeAccommodationId = tailormadeAccommodationId;
    }

    public String getTailormadeAccommodationDistrictId() {
        return tailormadeAccommodationDistrictId;
    }

    public void setTailormadeAccommodationDistrictId(String tailormadeAccommodationDistrictId) {
        this.tailormadeAccommodationDistrictId = tailormadeAccommodationDistrictId;
    }

    public String getTailormadeAccommodationDistrictName() {
        return tailormadeAccommodationDistrictName;
    }

    public void setTailormadeAccommodationDistrictName(String tailormadeAccommodationDistrictName) {
        this.tailormadeAccommodationDistrictName = tailormadeAccommodationDistrictName;
    }

    public String getTailormadeAccommodationTailormadeId() {
        return tailormadeAccommodationTailormadeId;
    }

    public void setTailormadeAccommodationTailormadeId(String tailormadeAccommodationTailormadeId) {
        this.tailormadeAccommodationTailormadeId = tailormadeAccommodationTailormadeId;
    }

    public String getTailormadeAccommodationAccommodationId() {
        return tailormadeAccommodationAccommodationId;
    }

    public void setTailormadeAccommodationAccommodationId(String tailormadeAccommodationAccommodationId) {
        this.tailormadeAccommodationAccommodationId = tailormadeAccommodationAccommodationId;
    }

    public String getTailormadeAccommodationAccommodationName() {
        return tailormadeAccommodationAccommodationName;
    }

    public void setTailormadeAccommodationAccommodationName(String tailormadeAccommodationAccommodationName) {
        this.tailormadeAccommodationAccommodationName = tailormadeAccommodationAccommodationName;
    }

    public String getTailormadeAccommodationRoomId() {
        return tailormadeAccommodationRoomId;
    }

    public void setTailormadeAccommodationRoomId(String tailormadeAccommodationRoomId) {
        this.tailormadeAccommodationRoomId = tailormadeAccommodationRoomId;
    }

    public Object getTailormadeAccommodationRoomTypeName() {
        return tailormadeAccommodationRoomTypeName;
    }

    public void setTailormadeAccommodationRoomTypeName(Object tailormadeAccommodationRoomTypeName) {
        this.tailormadeAccommodationRoomTypeName = tailormadeAccommodationRoomTypeName;
    }

    public String getTailormadeAccommodationAccommodationRoomMaxOccupancy() {
        return tailormadeAccommodationAccommodationRoomMaxOccupancy;
    }

    public void setTailormadeAccommodationAccommodationRoomMaxOccupancy(String tailormadeAccommodationAccommodationRoomMaxOccupancy) {
        this.tailormadeAccommodationAccommodationRoomMaxOccupancy = tailormadeAccommodationAccommodationRoomMaxOccupancy;
    }

    public String getTailormadeAccommodationAccommodationRoomPrice() {
        return tailormadeAccommodationAccommodationRoomPrice;
    }

    public void setTailormadeAccommodationAccommodationRoomPrice(String tailormadeAccommodationAccommodationRoomPrice) {
        this.tailormadeAccommodationAccommodationRoomPrice = tailormadeAccommodationAccommodationRoomPrice;
    }

    public String getTailormadeAccommodationAccommodationCategoryId() {
        return tailormadeAccommodationAccommodationCategoryId;
    }

    public void setTailormadeAccommodationAccommodationCategoryId(String tailormadeAccommodationAccommodationCategoryId) {
        this.tailormadeAccommodationAccommodationCategoryId = tailormadeAccommodationAccommodationCategoryId;
    }

    public String getTailormadeAccommodationAccommodationCategoryName() {
        return tailormadeAccommodationAccommodationCategoryName;
    }

    public void setTailormadeAccommodationAccommodationCategoryName(String tailormadeAccommodationAccommodationCategoryName) {
        this.tailormadeAccommodationAccommodationCategoryName = tailormadeAccommodationAccommodationCategoryName;
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