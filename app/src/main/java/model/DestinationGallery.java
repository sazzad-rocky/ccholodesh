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

public class DestinationGallery {

    @SerializedName("destination_gallery_id")
    @Expose
    private Integer destinationGalleryId;
    @SerializedName("destination_gallery_destination_id")
    @Expose
    private String destinationGalleryDestinationId;
    @SerializedName("destination_gallery_image")
    @Expose
    private String destinationGalleryImage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getDestinationGalleryId() {
        return destinationGalleryId;
    }


    public void setDestinationGalleryId(Integer destinationGalleryId) {
        this.destinationGalleryId = destinationGalleryId;
    }

    public String getDestinationGalleryDestinationId() {
        return destinationGalleryDestinationId;
    }

    public void setDestinationGalleryDestinationId(String destinationGalleryDestinationId) {
        this.destinationGalleryDestinationId = destinationGalleryDestinationId;
    }

    public String getDestinationGalleryImage() {
        return destinationGalleryImage;
    }

    public void setDestinationGalleryImage(String destinationGalleryImage) {
        this.destinationGalleryImage = destinationGalleryImage;
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
