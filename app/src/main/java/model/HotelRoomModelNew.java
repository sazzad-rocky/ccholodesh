/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rhythmshahriar on 10/29/17.
 */

public class HotelRoomModelNew {
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("provider_id")
    @Expose
    private String providerId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("accommodation_room_id")
    @Expose
    private String accommodationRoomId;
    @SerializedName("accommodation_room_max_occupancy")
    @Expose
    private String accommodationRoomMaxOccupancy;
    @SerializedName("accommodation_room_price")
    @Expose
    private String accommodationRoomPrice;
    @SerializedName("accommodation_room_gallery_image")
    @Expose
    private String accommodationRoomGalleryImage;
    @SerializedName("accommodation_room_gallery_id")
    @Expose
    private String accommodationRoomGalleryId;
    @SerializedName("accommodation_category_id")
    @Expose
    private String accommodationCategoryId;
    @SerializedName("accommodation_category_name")
    @Expose
    private String accommodationCategoryName;
    @SerializedName("bed_type_name")
    @Expose
    private List<BedTypeName> bedTypeName = null;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getAccommodationRoomId() {
        return accommodationRoomId;
    }

    public void setAccommodationRoomId(String accommodationRoomId) {
        this.accommodationRoomId = accommodationRoomId;
    }

    public String getAccommodationRoomMaxOccupancy() {
        return accommodationRoomMaxOccupancy;
    }

    public void setAccommodationRoomMaxOccupancy(String accommodationRoomMaxOccupancy) {
        this.accommodationRoomMaxOccupancy = accommodationRoomMaxOccupancy;
    }

    public String getAccommodationRoomPrice() {
        return accommodationRoomPrice;
    }

    public void setAccommodationRoomPrice(String accommodationRoomPrice) {
        this.accommodationRoomPrice = accommodationRoomPrice;
    }

    public String getAccommodationRoomGalleryImage() {
        return accommodationRoomGalleryImage;
    }

    public void setAccommodationRoomGalleryImage(String accommodationRoomGalleryImage) {
        this.accommodationRoomGalleryImage = accommodationRoomGalleryImage;
    }

    public String getAccommodationRoomGalleryId() {
        return accommodationRoomGalleryId;
    }

    public void setAccommodationRoomGalleryId(String accommodationRoomGalleryId) {
        this.accommodationRoomGalleryId = accommodationRoomGalleryId;
    }

    public String getAccommodationCategoryId() {
        return accommodationCategoryId;
    }

    public void setAccommodationCategoryId(String accommodationCategoryId) {
        this.accommodationCategoryId = accommodationCategoryId;
    }

    public String getAccommodationCategoryName() {
        return accommodationCategoryName;
    }

    public void setAccommodationCategoryName(String accommodationCategoryName) {
        this.accommodationCategoryName = accommodationCategoryName;
    }

    public List<BedTypeName> getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(List<BedTypeName> bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

}
