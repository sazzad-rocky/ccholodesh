package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccommodationProvider {
    private boolean isSelected;
    private int quantity=1;

    public int getQuantity() {
        return quantity;
    }

    public AccommodationProvider setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public AccommodationProvider setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }
    public int ctStart;

    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("destination_id")
    @Expose
    private Integer destinationId;
    @SerializedName("destination_name")
    @Expose
    private String destinationName;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("accommodation_service_id")
    @Expose
    private Integer accommodationServiceId;
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
    @SerializedName("accommodation_provider_id")
    @Expose
    private Integer accommodationProviderId;
    @SerializedName("accommodation_location_id")
    @Expose
    private Integer accommodationLocationId;
    @SerializedName("accommodation_service_address")
    @Expose
    private String accommodationServiceAddress;
    @SerializedName("accommodation_service_contact_no")
    @Expose
    private String accommodationServiceContactNo;
    @SerializedName("accommodation_service_email")
    @Expose
    private String accommodationServiceEmail;

    @SerializedName("provider_image")
    @Expose
    private String providerImage;

    @SerializedName("review_rating_average")
    @Expose
    public String reviewRatingAverage;

    public float review;


    //new bangla

    @SerializedName("district_name_bn")
    @Expose
    public String districtNameBn;
    @SerializedName("provider_name_bn")
    @Expose
    public String providerNameBn;
    @SerializedName("hotel_category_name_bn")
    @Expose
    public String categoryBn;
    @SerializedName("accommodation_type_name_bn")
    @Expose
    public String typeBn;
    @SerializedName("accommodation_service_address_bn")
    @Expose
    public String addressBn;

    @SerializedName("accommodation_room_max_price")
    @Expose
    public String maxPrice;
    @SerializedName("accommodation_room_min_price")
    @Expose
    public String minPrice;



    public String getProviderImage ()
    {
        return providerImage;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public Integer getAccommodationServiceId() {
        return accommodationServiceId;
    }

    public void setAccommodationServiceId(Integer accommodationServiceId) {
        this.accommodationServiceId = accommodationServiceId;
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

    public String getAccommodationServiceEmail() {
        return accommodationServiceEmail;
    }

    public void setAccommodationServiceEmail(String accommodationServiceEmail) {
        this.accommodationServiceEmail = accommodationServiceEmail;
    }


}