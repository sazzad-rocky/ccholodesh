package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelDetails {

    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;

    @SerializedName("provider_name_bn")
    @Expose
    private String providerNameBn;

    @SerializedName("destination_id")
    @Expose
    private Integer destinationId;
    @SerializedName("destination_name")
    @Expose
    private String destinationName;
    @SerializedName("hotel_category_id")
    @Expose
    private Integer hotelCategoryId;
    @SerializedName("hotel_category_name")
    @Expose
    private String hotelCategoryName;
    @SerializedName("accommodation_type_id")
    @Expose
    private Integer accommodationTypeId;
    @SerializedName("accommodation_type_name")
    @Expose
    private String accommodationTypeName;
    @SerializedName("accommodation_service_id")
    @Expose
    private Integer accommodationServiceId;
    @SerializedName("accommodation_provider_id")
    @Expose
    private Integer accommodationProviderId;
    @SerializedName("accommodation_location_id")
    @Expose
    private Integer accommodationLocationId;
    @SerializedName("accommodation_category_id")
    @Expose
    private Integer accommodationCategoryId;
    @SerializedName("accommodation_service_lat")
    @Expose
    private String accommodationServiceLat;
    @SerializedName("accommodation_service_long")
    @Expose
    private String accommodationServiceLong;
    @SerializedName("accommodation_service_name")
    @Expose
    private String accommodationServiceName;

    @SerializedName("accommodation_service_address")
    @Expose
    private String accommodationServiceAddress;
    @SerializedName("accommodation_service_contact_no")
    @Expose
    private String accommodationServiceContactNo;
    @SerializedName("accommodation_service_contact_person")
    @Expose
    private String accommodationServiceContactPerson;
    @SerializedName("accommodation_service_hotline")
    @Expose
    private String accommodationServiceHotline;
    @SerializedName("accommodation_service_email")
    @Expose
    private String accommodationServiceEmail;
    @SerializedName("accommodation_service_status")
    @Expose
    private String accommodationServiceStatus;
    @SerializedName("accommodation_service_details")
    @Expose
    private String accommodationServiceDetails;
    @SerializedName("accommodation_service_details_bn")
    @Expose
    private String accommodationServiceDetailsBn;

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("review_rating_average")
    @Expose
    private String ratingAverage;

    public String getRatingAverage ()
    {
        return ratingAverage;
    }

    @SerializedName("review_rating_count")
    @Expose
    private String ratingCount;

    @SerializedName("accommodation_service_address_bn")
    @Expose
    private String serviceAddressBn;



    public String getProviderNameBn(){return providerNameBn;}
    public String getServiceAddressBn ()
    {
        return serviceAddressBn;
    }
    public String getAccommodationServiceDetailsBn () {return accommodationServiceDetailsBn;}


    public String getRatingCount ()
    {
        return ratingCount;
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

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Integer getHotelCategoryId() {
        return hotelCategoryId;
    }

    public void setHotelCategoryId(Integer hotelCategoryId) {
        this.hotelCategoryId = hotelCategoryId;
    }

    public String getHotelCategoryName() {
        return hotelCategoryName;
    }

    public void setHotelCategoryName(String hotelCategoryName) {
        this.hotelCategoryName = hotelCategoryName;
    }

    public Integer getAccommodationTypeId() {
        return accommodationTypeId;
    }

    public void setAccommodationTypeId(Integer accommodationTypeId) {
        this.accommodationTypeId = accommodationTypeId;
    }

    public String getAccommodationTypeName() {
        return accommodationTypeName;
    }

    public void setAccommodationTypeName(String accommodationTypeName) {
        this.accommodationTypeName = accommodationTypeName;
    }

    public Integer getAccommodationServiceId() {
        return accommodationServiceId;
    }

    public void setAccommodationServiceId(Integer accommodationServiceId) {
        this.accommodationServiceId = accommodationServiceId;
    }

    public Integer getAccommodationProviderId() {
        return accommodationProviderId;
    }

    public void setAccommodationProviderId(Integer accommodationProviderId) {
        this.accommodationProviderId = accommodationProviderId;
    }

    public Integer getAccommodationLocationId() {
        return accommodationLocationId;
    }

    public void setAccommodationLocationId(Integer accommodationLocationId) {
        this.accommodationLocationId = accommodationLocationId;
    }

    public Integer getAccommodationCategoryId() {
        return accommodationCategoryId;
    }

    public void setAccommodationCategoryId(Integer accommodationCategoryId) {
        this.accommodationCategoryId = accommodationCategoryId;
    }

    public String getAccommodationServiceLat() {
        return accommodationServiceLat;
    }

    public void setAccommodationServiceLat(String accommodationServiceLat) {
        this.accommodationServiceLat = accommodationServiceLat;
    }

    public String getAccommodationServiceLong() {
        return accommodationServiceLong;
    }

    public void setAccommodationServiceLong(String accommodationServiceLong) {
        this.accommodationServiceLong = accommodationServiceLong;
    }

    public String getAccommodationServiceName() {
        return accommodationServiceName;
    }

    public void setAccommodationServiceName(String accommodationServiceName) {
        this.accommodationServiceName = accommodationServiceName;
    }

    public String getAccommodationServiceAddress() {
        return accommodationServiceAddress;
    }

    public void setAccommodationServiceAddress(String accommodationServiceAddress) {
        this.accommodationServiceAddress = accommodationServiceAddress;
    }

    public String getAccommodationServiceContactNo() {
        return accommodationServiceContactNo;
    }

    public void setAccommodationServiceContactNo(String accommodationServiceContactNo) {
        this.accommodationServiceContactNo = accommodationServiceContactNo;
    }

    public String getAccommodationServiceContactPerson() {
        return accommodationServiceContactPerson;
    }

    public void setAccommodationServiceContactPerson(String accommodationServiceContactPerson) {
        this.accommodationServiceContactPerson = accommodationServiceContactPerson;
    }

    public String getAccommodationServiceHotline() {
        return accommodationServiceHotline;
    }

    public void setAccommodationServiceHotline(String accommodationServiceHotline) {
        this.accommodationServiceHotline = accommodationServiceHotline;
    }

    public String getAccommodationServiceEmail() {
        return accommodationServiceEmail;
    }

    public void setAccommodationServiceEmail(String accommodationServiceEmail) {
        this.accommodationServiceEmail = accommodationServiceEmail;
    }

    public String getAccommodationServiceStatus() {
        return accommodationServiceStatus;
    }

    public void setAccommodationServiceStatus(String accommodationServiceStatus) {
        this.accommodationServiceStatus = accommodationServiceStatus;
    }

    public String getAccommodationServiceDetails() {
        return accommodationServiceDetails;
    }

    public void setAccommodationServiceDetails(String accommodationServiceDetails) {
        this.accommodationServiceDetails = accommodationServiceDetails;
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