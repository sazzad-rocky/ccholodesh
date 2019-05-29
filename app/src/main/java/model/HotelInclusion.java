package model;

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

public class HotelInclusion {

    @SerializedName("accommodation_service_id")
    @Expose
    private Integer accommodationServiceId;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("destination_id")
    @Expose
    private Integer destinationId;
    @SerializedName("destination_name")
    @Expose
    private String destinationName;
    @SerializedName("accommodation_facility_type_id")
    @Expose
    private Integer accommodationFacilityTypeId;
    @SerializedName("hotel_facility_id")
    @Expose
    private Integer hotelFacilityId;
    @SerializedName("hotel_facility_name")
    @Expose
    private String hotelFacilityName;

    @SerializedName("hotel_facility_name_bn")
    @Expose
    public String hotelFacilityNameBn;

    public Integer getAccommodationServiceId() {
        return accommodationServiceId;
    }

    public void setAccommodationServiceId(Integer accommodationServiceId) {
        this.accommodationServiceId = accommodationServiceId;
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

    public Integer getAccommodationFacilityTypeId() {
        return accommodationFacilityTypeId;
    }

    public void setAccommodationFacilityTypeId(Integer accommodationFacilityTypeId) {
        this.accommodationFacilityTypeId = accommodationFacilityTypeId;
    }

    public Integer getHotelFacilityId() {
        return hotelFacilityId;
    }

    public void setHotelFacilityId(Integer hotelFacilityId) {
        this.hotelFacilityId = hotelFacilityId;
    }

    public String getHotelFacilityName() {
        return hotelFacilityName;
    }

    public void setHotelFacilityName(String hotelFacilityName) {
        this.hotelFacilityName = hotelFacilityName;
    }

}