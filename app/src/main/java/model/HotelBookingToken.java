package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelBookingToken {

    @SerializedName("accommodation_booking_id")
    @Expose
    private Integer accommodationBookingId;
    @SerializedName("accommodation_booking_token")
    @Expose
    private String accommodationBookingToken;
    @SerializedName("accommodation_booking_user_id")
    @Expose
    private String accommodationBookingUserId;
    @SerializedName("accommodation_booking_service_id")
    @Expose
    private Object accommodationBookingServiceId;
    @SerializedName("accommodation_booking_district_id")
    @Expose
    private String accommodationBookingDistrictId;

    @Override
    public String toString() {
        return accommodationBookingToken;
    }

    @SerializedName("accommodation_booking_provider_id")
    @Expose
    private String accommodationBookingProviderId;
    @SerializedName("accommodation_booking_room_id")
    @Expose
    private String accommodationBookingRoomId;
    @SerializedName("accommodation_booking_room_quantity")
    @Expose
    private String accommodationBookingRoomQuantity;
    @SerializedName("accommodation_booking_room_type")
    @Expose
    private Object accommodationBookingRoomType;
    @SerializedName("accommodation_booking_room_max_occupancy")
    @Expose
    private String accommodationBookingRoomMaxOccupancy;
    @SerializedName("accommodation_booking_room_price")
    @Expose
    private String accommodationBookingRoomPrice;
    @SerializedName("accommodation_booking_category_id")
    @Expose
    private String accommodationBookingCategoryId;
    @SerializedName("accommodation_booking_status")
    @Expose
    private String accommodationBookingStatus;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("customer_name")
    @Expose
    private Object customerName;
    @SerializedName("customer_email")
    @Expose
    private String customerEmail;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("customer_phone")
    @Expose
    private String customerPhone;
    @SerializedName("customer_address")
    @Expose
    private Object customerAddress;
    @SerializedName("customer_status")
    @Expose
    private String customerStatus;
    @SerializedName("accommodation_booking_total_id")
    @Expose
    private String accommodationBookingTotalId;
    @SerializedName("accommodation_booking_total_token")
    @Expose
    private String accommodationBookingTotalToken;
    @SerializedName("accommodation_booking_total_price")
    @Expose
    private String accommodationBookingTotalPrice;
    @SerializedName("provider_id")
    @Expose
    private String providerId;
    @SerializedName("provider_user_id")
    @Expose
    private Object providerUserId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("provider_email")
    @Expose
    private String providerEmail;
    @SerializedName("provider_address")
    @Expose
    private String providerAddress;
    @SerializedName("provider_phone")
    @Expose
    private String providerPhone;
    @SerializedName("provider_website")
    @Expose
    private String providerWebsite;
    @SerializedName("provider_hotline")
    @Expose
    private String providerHotline;
    @SerializedName("provider_establish_year")
    @Expose
    private Object providerEstablishYear;
    @SerializedName("provider_document")
    @Expose
    private Object providerDocument;
    @SerializedName("provider_aggrement_percentage")
    @Expose
    private String providerAggrementPercentage;
    @SerializedName("provider_aggrement_start_date")
    @Expose
    private String providerAggrementStartDate;
    @SerializedName("provider_aggrement_end_date")
    @Expose
    private String providerAggrementEndDate;
    @SerializedName("provider_tin_no")
    @Expose
    private String providerTinNo;
    @SerializedName("provider_license_no")
    @Expose
    private String providerLicenseNo;
    @SerializedName("provider_image")
    @Expose
    private String providerImage;
    @SerializedName("provider_status")
    @Expose
    private String providerStatus;
    @SerializedName("provider_type")
    @Expose
    private String providerType;
    @SerializedName("provider_name_bn")
    @Expose
    private Object providerNameBn;
    @SerializedName("provider_address_bn")
    @Expose
    private Object providerAddressBn;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("accommodation_service_id")
    @Expose
    private Object accommodationServiceId;
    @SerializedName("accommodation_provider_id")
    @Expose
    private Object accommodationProviderId;
    @SerializedName("accommodation_location_id")
    @Expose
    private Object accommodationLocationId;
    @SerializedName("accommodation_category_id")
    @Expose
    private Object accommodationCategoryId;
    @SerializedName("accommodation_type_id")
    @Expose
    private Object accommodationTypeId;
    @SerializedName("accommodation_service_lat")
    @Expose
    private Object accommodationServiceLat;
    @SerializedName("accommodation_service_long")
    @Expose
    private Object accommodationServiceLong;
    @SerializedName("accommodation_service_name")
    @Expose
    private Object accommodationServiceName;
    @SerializedName("accommodation_service_address")
    @Expose
    private Object accommodationServiceAddress;
    @SerializedName("accommodation_service_address_bn")
    @Expose
    private Object accommodationServiceAddressBn;
    @SerializedName("accommodation_service_contact_no")
    @Expose
    private Object accommodationServiceContactNo;
    @SerializedName("accommodation_service_contact_person")
    @Expose
    private Object accommodationServiceContactPerson;
    @SerializedName("accommodation_service_hotline")
    @Expose
    private Object accommodationServiceHotline;
    @SerializedName("accommodation_service_email")
    @Expose
    private Object accommodationServiceEmail;
    @SerializedName("accommodation_service_status")
    @Expose
    private Object accommodationServiceStatus;
    @SerializedName("accommodation_service_details")
    @Expose
    private Object accommodationServiceDetails;
    @SerializedName("accommodation_service_details_bn")
    @Expose
    private Object accommodationServiceDetailsBn;
    @SerializedName("destination_id")
    @Expose
    private Object destinationId;
    @SerializedName("destination_division_id")
    @Expose
    private Object destinationDivisionId;
    @SerializedName("destination_district_id")
    @Expose
    private Object destinationDistrictId;
    @SerializedName("destination_name")
    @Expose
    private Object destinationName;
    @SerializedName("destination_name_bn")
    @Expose
    private Object destinationNameBn;
    @SerializedName("destination_details")
    @Expose
    private Object destinationDetails;
    @SerializedName("destination_image")
    @Expose
    private Object destinationImage;
    @SerializedName("destination_lat")
    @Expose
    private Object destinationLat;
    @SerializedName("destination_long")
    @Expose
    private Object destinationLong;
    @SerializedName("destination_tags")
    @Expose
    private Object destinationTags;
    @SerializedName("destination_status")
    @Expose
    private Object destinationStatus;
    @SerializedName("destination_details_bn")
    @Expose
    private Object destinationDetailsBn;
    @SerializedName("destination_tags_bn")
    @Expose
    private Object destinationTagsBn;
    @SerializedName("hotel_category_id")
    @Expose
    private Object hotelCategoryId;
    @SerializedName("hotel_category_name")
    @Expose
    private Object hotelCategoryName;
    @SerializedName("hotel_category_name_bn")
    @Expose
    private Object hotelCategoryNameBn;
    @SerializedName("hotel_category_status")
    @Expose
    private Object hotelCategoryStatus;
    @SerializedName("accommodation_type_name")
    @Expose
    private Object accommodationTypeName;
    @SerializedName("accommodation_type_name_bn")
    @Expose
    private Object accommodationTypeNameBn;
    @SerializedName("accommodation_type_status")
    @Expose
    private Object accommodationTypeStatus;

    public Integer getAccommodationBookingId() {
        return accommodationBookingId;
    }

    public void setAccommodationBookingId(Integer accommodationBookingId) {
        this.accommodationBookingId = accommodationBookingId;
    }

    public String getAccommodationBookingToken() {
        return accommodationBookingToken;
    }

    public void setAccommodationBookingToken(String accommodationBookingToken) {
        this.accommodationBookingToken = accommodationBookingToken;
    }

    public String getAccommodationBookingUserId() {
        return accommodationBookingUserId;
    }

    public void setAccommodationBookingUserId(String accommodationBookingUserId) {
        this.accommodationBookingUserId = accommodationBookingUserId;
    }

    public Object getAccommodationBookingServiceId() {
        return accommodationBookingServiceId;
    }

    public void setAccommodationBookingServiceId(Object accommodationBookingServiceId) {
        this.accommodationBookingServiceId = accommodationBookingServiceId;
    }

    public String getAccommodationBookingDistrictId() {
        return accommodationBookingDistrictId;
    }

    public void setAccommodationBookingDistrictId(String accommodationBookingDistrictId) {
        this.accommodationBookingDistrictId = accommodationBookingDistrictId;
    }

    public String getAccommodationBookingProviderId() {
        return accommodationBookingProviderId;
    }

    public void setAccommodationBookingProviderId(String accommodationBookingProviderId) {
        this.accommodationBookingProviderId = accommodationBookingProviderId;
    }

    public String getAccommodationBookingRoomId() {
        return accommodationBookingRoomId;
    }

    public void setAccommodationBookingRoomId(String accommodationBookingRoomId) {
        this.accommodationBookingRoomId = accommodationBookingRoomId;
    }

    public String getAccommodationBookingRoomQuantity() {
        return accommodationBookingRoomQuantity;
    }

    public void setAccommodationBookingRoomQuantity(String accommodationBookingRoomQuantity) {
        this.accommodationBookingRoomQuantity = accommodationBookingRoomQuantity;
    }

    public Object getAccommodationBookingRoomType() {
        return accommodationBookingRoomType;
    }

    public void setAccommodationBookingRoomType(Object accommodationBookingRoomType) {
        this.accommodationBookingRoomType = accommodationBookingRoomType;
    }

    public String getAccommodationBookingRoomMaxOccupancy() {
        return accommodationBookingRoomMaxOccupancy;
    }

    public void setAccommodationBookingRoomMaxOccupancy(String accommodationBookingRoomMaxOccupancy) {
        this.accommodationBookingRoomMaxOccupancy = accommodationBookingRoomMaxOccupancy;
    }

    public String getAccommodationBookingRoomPrice() {
        return accommodationBookingRoomPrice;
    }

    public void setAccommodationBookingRoomPrice(String accommodationBookingRoomPrice) {
        this.accommodationBookingRoomPrice = accommodationBookingRoomPrice;
    }

    public String getAccommodationBookingCategoryId() {
        return accommodationBookingCategoryId;
    }

    public void setAccommodationBookingCategoryId(String accommodationBookingCategoryId) {
        this.accommodationBookingCategoryId = accommodationBookingCategoryId;
    }

    public String getAccommodationBookingStatus() {
        return accommodationBookingStatus;
    }

    public void setAccommodationBookingStatus(String accommodationBookingStatus) {
        this.accommodationBookingStatus = accommodationBookingStatus;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Object getCustomerName() {
        return customerName;
    }

    public void setCustomerName(Object customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Object getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Object customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getAccommodationBookingTotalId() {
        return accommodationBookingTotalId;
    }

    public void setAccommodationBookingTotalId(String accommodationBookingTotalId) {
        this.accommodationBookingTotalId = accommodationBookingTotalId;
    }

    public String getAccommodationBookingTotalToken() {
        return accommodationBookingTotalToken;
    }

    public void setAccommodationBookingTotalToken(String accommodationBookingTotalToken) {
        this.accommodationBookingTotalToken = accommodationBookingTotalToken;
    }

    public String getAccommodationBookingTotalPrice() {
        return accommodationBookingTotalPrice;
    }

    public void setAccommodationBookingTotalPrice(String accommodationBookingTotalPrice) {
        this.accommodationBookingTotalPrice = accommodationBookingTotalPrice;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Object getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(Object providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }

    public String getProviderWebsite() {
        return providerWebsite;
    }

    public void setProviderWebsite(String providerWebsite) {
        this.providerWebsite = providerWebsite;
    }

    public String getProviderHotline() {
        return providerHotline;
    }

    public void setProviderHotline(String providerHotline) {
        this.providerHotline = providerHotline;
    }

    public Object getProviderEstablishYear() {
        return providerEstablishYear;
    }

    public void setProviderEstablishYear(Object providerEstablishYear) {
        this.providerEstablishYear = providerEstablishYear;
    }

    public Object getProviderDocument() {
        return providerDocument;
    }

    public void setProviderDocument(Object providerDocument) {
        this.providerDocument = providerDocument;
    }

    public String getProviderAggrementPercentage() {
        return providerAggrementPercentage;
    }

    public void setProviderAggrementPercentage(String providerAggrementPercentage) {
        this.providerAggrementPercentage = providerAggrementPercentage;
    }

    public String getProviderAggrementStartDate() {
        return providerAggrementStartDate;
    }

    public void setProviderAggrementStartDate(String providerAggrementStartDate) {
        this.providerAggrementStartDate = providerAggrementStartDate;
    }

    public String getProviderAggrementEndDate() {
        return providerAggrementEndDate;
    }

    public void setProviderAggrementEndDate(String providerAggrementEndDate) {
        this.providerAggrementEndDate = providerAggrementEndDate;
    }

    public String getProviderTinNo() {
        return providerTinNo;
    }

    public void setProviderTinNo(String providerTinNo) {
        this.providerTinNo = providerTinNo;
    }

    public String getProviderLicenseNo() {
        return providerLicenseNo;
    }

    public void setProviderLicenseNo(String providerLicenseNo) {
        this.providerLicenseNo = providerLicenseNo;
    }

    public String getProviderImage() {
        return providerImage;
    }

    public void setProviderImage(String providerImage) {
        this.providerImage = providerImage;
    }

    public String getProviderStatus() {
        return providerStatus;
    }

    public void setProviderStatus(String providerStatus) {
        this.providerStatus = providerStatus;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public Object getProviderNameBn() {
        return providerNameBn;
    }

    public void setProviderNameBn(Object providerNameBn) {
        this.providerNameBn = providerNameBn;
    }

    public Object getProviderAddressBn() {
        return providerAddressBn;
    }

    public void setProviderAddressBn(Object providerAddressBn) {
        this.providerAddressBn = providerAddressBn;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Object getAccommodationServiceId() {
        return accommodationServiceId;
    }

    public void setAccommodationServiceId(Object accommodationServiceId) {
        this.accommodationServiceId = accommodationServiceId;
    }

    public Object getAccommodationProviderId() {
        return accommodationProviderId;
    }

    public void setAccommodationProviderId(Object accommodationProviderId) {
        this.accommodationProviderId = accommodationProviderId;
    }

    public Object getAccommodationLocationId() {
        return accommodationLocationId;
    }

    public void setAccommodationLocationId(Object accommodationLocationId) {
        this.accommodationLocationId = accommodationLocationId;
    }

    public Object getAccommodationCategoryId() {
        return accommodationCategoryId;
    }

    public void setAccommodationCategoryId(Object accommodationCategoryId) {
        this.accommodationCategoryId = accommodationCategoryId;
    }

    public Object getAccommodationTypeId() {
        return accommodationTypeId;
    }

    public void setAccommodationTypeId(Object accommodationTypeId) {
        this.accommodationTypeId = accommodationTypeId;
    }

    public Object getAccommodationServiceLat() {
        return accommodationServiceLat;
    }

    public void setAccommodationServiceLat(Object accommodationServiceLat) {
        this.accommodationServiceLat = accommodationServiceLat;
    }

    public Object getAccommodationServiceLong() {
        return accommodationServiceLong;
    }

    public void setAccommodationServiceLong(Object accommodationServiceLong) {
        this.accommodationServiceLong = accommodationServiceLong;
    }

    public Object getAccommodationServiceName() {
        return accommodationServiceName;
    }

    public void setAccommodationServiceName(Object accommodationServiceName) {
        this.accommodationServiceName = accommodationServiceName;
    }

    public Object getAccommodationServiceAddress() {
        return accommodationServiceAddress;
    }

    public void setAccommodationServiceAddress(Object accommodationServiceAddress) {
        this.accommodationServiceAddress = accommodationServiceAddress;
    }

    public Object getAccommodationServiceAddressBn() {
        return accommodationServiceAddressBn;
    }

    public void setAccommodationServiceAddressBn(Object accommodationServiceAddressBn) {
        this.accommodationServiceAddressBn = accommodationServiceAddressBn;
    }

    public Object getAccommodationServiceContactNo() {
        return accommodationServiceContactNo;
    }

    public void setAccommodationServiceContactNo(Object accommodationServiceContactNo) {
        this.accommodationServiceContactNo = accommodationServiceContactNo;
    }

    public Object getAccommodationServiceContactPerson() {
        return accommodationServiceContactPerson;
    }

    public void setAccommodationServiceContactPerson(Object accommodationServiceContactPerson) {
        this.accommodationServiceContactPerson = accommodationServiceContactPerson;
    }

    public Object getAccommodationServiceHotline() {
        return accommodationServiceHotline;
    }

    public void setAccommodationServiceHotline(Object accommodationServiceHotline) {
        this.accommodationServiceHotline = accommodationServiceHotline;
    }

    public Object getAccommodationServiceEmail() {
        return accommodationServiceEmail;
    }

    public void setAccommodationServiceEmail(Object accommodationServiceEmail) {
        this.accommodationServiceEmail = accommodationServiceEmail;
    }

    public Object getAccommodationServiceStatus() {
        return accommodationServiceStatus;
    }

    public void setAccommodationServiceStatus(Object accommodationServiceStatus) {
        this.accommodationServiceStatus = accommodationServiceStatus;
    }

    public Object getAccommodationServiceDetails() {
        return accommodationServiceDetails;
    }

    public void setAccommodationServiceDetails(Object accommodationServiceDetails) {
        this.accommodationServiceDetails = accommodationServiceDetails;
    }

    public Object getAccommodationServiceDetailsBn() {
        return accommodationServiceDetailsBn;
    }

    public void setAccommodationServiceDetailsBn(Object accommodationServiceDetailsBn) {
        this.accommodationServiceDetailsBn = accommodationServiceDetailsBn;
    }

    public Object getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Object destinationId) {
        this.destinationId = destinationId;
    }

    public Object getDestinationDivisionId() {
        return destinationDivisionId;
    }

    public void setDestinationDivisionId(Object destinationDivisionId) {
        this.destinationDivisionId = destinationDivisionId;
    }

    public Object getDestinationDistrictId() {
        return destinationDistrictId;
    }

    public void setDestinationDistrictId(Object destinationDistrictId) {
        this.destinationDistrictId = destinationDistrictId;
    }

    public Object getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(Object destinationName) {
        this.destinationName = destinationName;
    }

    public Object getDestinationNameBn() {
        return destinationNameBn;
    }

    public void setDestinationNameBn(Object destinationNameBn) {
        this.destinationNameBn = destinationNameBn;
    }

    public Object getDestinationDetails() {
        return destinationDetails;
    }

    public void setDestinationDetails(Object destinationDetails) {
        this.destinationDetails = destinationDetails;
    }

    public Object getDestinationImage() {
        return destinationImage;
    }

    public void setDestinationImage(Object destinationImage) {
        this.destinationImage = destinationImage;
    }

    public Object getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(Object destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Object getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(Object destinationLong) {
        this.destinationLong = destinationLong;
    }

    public Object getDestinationTags() {
        return destinationTags;
    }

    public void setDestinationTags(Object destinationTags) {
        this.destinationTags = destinationTags;
    }

    public Object getDestinationStatus() {
        return destinationStatus;
    }

    public void setDestinationStatus(Object destinationStatus) {
        this.destinationStatus = destinationStatus;
    }

    public Object getDestinationDetailsBn() {
        return destinationDetailsBn;
    }

    public void setDestinationDetailsBn(Object destinationDetailsBn) {
        this.destinationDetailsBn = destinationDetailsBn;
    }

    public Object getDestinationTagsBn() {
        return destinationTagsBn;
    }

    public void setDestinationTagsBn(Object destinationTagsBn) {
        this.destinationTagsBn = destinationTagsBn;
    }

    public Object getHotelCategoryId() {
        return hotelCategoryId;
    }

    public void setHotelCategoryId(Object hotelCategoryId) {
        this.hotelCategoryId = hotelCategoryId;
    }

    public Object getHotelCategoryName() {
        return hotelCategoryName;
    }

    public void setHotelCategoryName(Object hotelCategoryName) {
        this.hotelCategoryName = hotelCategoryName;
    }

    public Object getHotelCategoryNameBn() {
        return hotelCategoryNameBn;
    }

    public void setHotelCategoryNameBn(Object hotelCategoryNameBn) {
        this.hotelCategoryNameBn = hotelCategoryNameBn;
    }

    public Object getHotelCategoryStatus() {
        return hotelCategoryStatus;
    }

    public void setHotelCategoryStatus(Object hotelCategoryStatus) {
        this.hotelCategoryStatus = hotelCategoryStatus;
    }

    public Object getAccommodationTypeName() {
        return accommodationTypeName;
    }

    public void setAccommodationTypeName(Object accommodationTypeName) {
        this.accommodationTypeName = accommodationTypeName;
    }

    public Object getAccommodationTypeNameBn() {
        return accommodationTypeNameBn;
    }

    public void setAccommodationTypeNameBn(Object accommodationTypeNameBn) {
        this.accommodationTypeNameBn = accommodationTypeNameBn;
    }

    public Object getAccommodationTypeStatus() {
        return accommodationTypeStatus;
    }

    public void setAccommodationTypeStatus(Object accommodationTypeStatus) {
        this.accommodationTypeStatus = accommodationTypeStatus;
    }

}