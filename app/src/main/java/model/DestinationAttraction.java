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

public class DestinationAttraction {

    @SerializedName("destination_attraction_id")
    @Expose
    private Integer destinationAttractionId;
    @SerializedName("destination_attraction_title")
    @Expose
    private String destinationAttractionTitle;
    @SerializedName("destination_attraction_title_bn")
    @Expose
    private String destinationAttractionTitleBn;
    @SerializedName("destination_attraction_details_bn")
    @Expose
    private String destinationAttractionDetailsBn;
    @SerializedName("destination_attraction_details")
    @Expose
    private String destinationAttractionDetails;
    @SerializedName("destination_attraction_image")
    @Expose
    private String destinationAttractionImage;
    @SerializedName("destination_id")
    @Expose
    private String destinationId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @Override
    public String toString() {
        return destinationAttractionTitle;
    }

    public Integer getDestinationAttractionId() {
        return destinationAttractionId;
    }

    public void setDestinationAttractionId(Integer destinationAttractionId) {
        this.destinationAttractionId = destinationAttractionId;
    }

    public String getDestinationAttractionTitle() {
        return destinationAttractionTitle;
    }

    public void setDestinationAttractionTitle(String destinationAttractionTitle) {
        this.destinationAttractionTitle = destinationAttractionTitle;
    }

    public String getDestinationAttractionTitleBn() {
        return destinationAttractionTitleBn;
    }

    public void setDestinationAttractionTitleBn(String destinationAttractionTitleBn) {
        this.destinationAttractionTitleBn = destinationAttractionTitleBn;
    }

    public String getDestinationAttractionDetailsBn() {
        return destinationAttractionDetailsBn;
    }

    public void setDestinationAttractionDetailsBn(String destinationAttractionDetailsBn) {
        this.destinationAttractionDetailsBn = destinationAttractionDetailsBn;
    }

    public String getDestinationAttractionDetails() {
        return destinationAttractionDetails;
    }

    public void setDestinationAttractionDetails(String destinationAttractionDetails) {
        this.destinationAttractionDetails = destinationAttractionDetails;
    }

    public String getDestinationAttractionImage() {
        return destinationAttractionImage;
    }

    public void setDestinationAttractionImage(String destinationAttractionImage) {
        this.destinationAttractionImage = destinationAttractionImage;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
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
