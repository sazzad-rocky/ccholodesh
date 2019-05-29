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

public class NearByPlaceGallerInfo {

    @SerializedName("destination_nearby_place_gallery_id")
    @Expose
    private Integer destinationNearbyPlaceGalleryId;
    @SerializedName("destination_nearby_place_id")
    @Expose
    private String destinationNearbyPlaceId;
    @SerializedName("destination_nearby_place_gallery_image")
    @Expose
    private String destinationNearbyPlaceGalleryImage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getDestinationNearbyPlaceGalleryId() {
        return destinationNearbyPlaceGalleryId;
    }

    public void setDestinationNearbyPlaceGalleryId(Integer destinationNearbyPlaceGalleryId) {
        this.destinationNearbyPlaceGalleryId = destinationNearbyPlaceGalleryId;
    }

    public String getDestinationNearbyPlaceId() {
        return destinationNearbyPlaceId;
    }

    public void setDestinationNearbyPlaceId(String destinationNearbyPlaceId) {
        this.destinationNearbyPlaceId = destinationNearbyPlaceId;
    }

    public String getDestinationNearbyPlaceGalleryImage() {
        return destinationNearbyPlaceGalleryImage;
    }

    public void setDestinationNearbyPlaceGalleryImage(String destinationNearbyPlaceGalleryImage) {
        this.destinationNearbyPlaceGalleryImage = destinationNearbyPlaceGalleryImage;
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
