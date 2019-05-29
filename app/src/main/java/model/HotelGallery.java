package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelGallery {

    @SerializedName("destination_name")
    @Expose
    private String destinationName;
    @SerializedName("destination_id")
    @Expose
    private Integer destinationId;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("accommodation_gallery_id")
    @Expose
    private Integer accommodationGalleryId;
    @SerializedName("accommodation_gallery_image")
    @Expose
    private String accommodationGalleryImage;

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getAccommodationGalleryId() {
        return accommodationGalleryId;
    }

    public void setAccommodationGalleryId(Integer accommodationGalleryId) {
        this.accommodationGalleryId = accommodationGalleryId;
    }

    public String getAccommodationGalleryImage() {
        return accommodationGalleryImage;
    }

    public void setAccommodationGalleryImage(String accommodationGalleryImage) {
        this.accommodationGalleryImage = accommodationGalleryImage;
    }

}