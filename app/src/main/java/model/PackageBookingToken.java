

package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageBookingToken {

    @SerializedName("package_booking_id")
    @Expose
    private Integer packageBookingId;
    @SerializedName("package_booking_package_id")
    @Expose
    private String packageBookingPackageId;
    @SerializedName("package_booking_token")
    @Expose
    private String packageBookingToken;
    @SerializedName("package_booking_user_id")
    @Expose
    private String packageBookingUserId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
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
    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("package_provider_id")
    @Expose
    private String packageProviderId;
    @SerializedName("package_destination_id")
    @Expose
    private String packageDestinationId;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("package_day")
    @Expose
    private String packageDay;
    @SerializedName("package_night")
    @Expose
    private String packageNight;
    @SerializedName("package_location_from")
    @Expose
    private String packageLocationFrom;
    @SerializedName("package_location_to")
    @Expose
    private String packageLocationTo;
    @SerializedName("package_location_route")
    @Expose
    private String packageLocationRoute;
    @SerializedName("package_available_start_date")
    @Expose
    private String packageAvailableStartDate;
    @SerializedName("package_available_end_date")
    @Expose
    private String packageAvailableEndDate;
    @SerializedName("package_overview")
    @Expose
    private String packageOverview;
    @SerializedName("package_cancellation_policy")
    @Expose
    private String packageCancellationPolicy;
    @SerializedName("package_remarks")
    @Expose
    private String packageRemarks;
    @SerializedName("package_activities")
    @Expose
    private String packageActivities;
    @SerializedName("package_do")
    @Expose
    private String packageDo;
    @SerializedName("package_dont")
    @Expose
    private String packageDont;
    @SerializedName("package_price")
    @Expose
    private String packagePrice;
    @SerializedName("package_discount")
    @Expose
    private String packageDiscount;
    @SerializedName("package_name_bn")
    @Expose
    private Object packageNameBn;
    @SerializedName("package_location_route_bn")
    @Expose
    private Object packageLocationRouteBn;
    @SerializedName("package_overview_bn")
    @Expose
    private Object packageOverviewBn;
    @SerializedName("package_cancellation_policy_bn")
    @Expose
    private Object packageCancellationPolicyBn;
    @SerializedName("package_remarks_bn")
    @Expose
    private Object packageRemarksBn;
    @SerializedName("package_activities_bn")
    @Expose
    private Object packageActivitiesBn;
    @SerializedName("package_do_bn")
    @Expose
    private Object packageDoBn;
    @SerializedName("package_dont_bn")
    @Expose
    private Object packageDontBn;
    @SerializedName("package_status")
    @Expose
    private String packageStatus;
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
    private String providerNameBn;
    @SerializedName("provider_address_bn")
    @Expose
    private String providerAddressBn;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("destination_id")
    @Expose
    private String destinationId;
    @SerializedName("destination_division_id")
    @Expose
    private String destinationDivisionId;
    @SerializedName("destination_district_id")
    @Expose
    private String destinationDistrictId;
    @SerializedName("destination_name")
    @Expose
    private String destinationName;
    @SerializedName("destination_name_bn")
    @Expose
    private String destinationNameBn;
    @SerializedName("destination_details")
    @Expose
    private String destinationDetails;
    @SerializedName("destination_image")
    @Expose
    private String destinationImage;
    @SerializedName("destination_lat")
    @Expose
    private String destinationLat;
    @SerializedName("destination_long")
    @Expose
    private String destinationLong;
    @SerializedName("destination_tags")
    @Expose
    private Object destinationTags;
    @SerializedName("destination_status")
    @Expose
    private String destinationStatus;
    @SerializedName("destination_details_bn")
    @Expose
    private Object destinationDetailsBn;
    @SerializedName("destination_tags_bn")
    @Expose
    private Object destinationTagsBn;

    @SerializedName("package_file")
    @Expose
    private String package_file;
    public String getpackage_file() {
        return package_file;
    }

    public void setpackage_file(String packageBookingId) {
        this.package_file = packageBookingId;
    }
    public Integer getPackageBookingId() {
        return packageBookingId;
    }

    public void setPackageBookingId(Integer packageBookingId) {
        this.packageBookingId = packageBookingId;
    }

    public String getPackageBookingPackageId() {
        return packageBookingPackageId;
    }

    public void setPackageBookingPackageId(String packageBookingPackageId) {
        this.packageBookingPackageId = packageBookingPackageId;
    }

    public String getPackageBookingToken() {
        return packageBookingToken;
    }

    public void setPackageBookingToken(String packageBookingToken) {
        this.packageBookingToken = packageBookingToken;
    }

    public String getPackageBookingUserId() {
        return packageBookingUserId;
    }

    public void setPackageBookingUserId(String packageBookingUserId) {
        this.packageBookingUserId = packageBookingUserId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
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

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageProviderId() {
        return packageProviderId;
    }

    public void setPackageProviderId(String packageProviderId) {
        this.packageProviderId = packageProviderId;
    }

    public String getPackageDestinationId() {
        return packageDestinationId;
    }

    public void setPackageDestinationId(String packageDestinationId) {
        this.packageDestinationId = packageDestinationId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageDay() {
        return packageDay;
    }

    public void setPackageDay(String packageDay) {
        this.packageDay = packageDay;
    }

    public String getPackageNight() {
        return packageNight;
    }

    public void setPackageNight(String packageNight) {
        this.packageNight = packageNight;
    }

    public String getPackageLocationFrom() {
        return packageLocationFrom;
    }

    public void setPackageLocationFrom(String packageLocationFrom) {
        this.packageLocationFrom = packageLocationFrom;
    }

    public String getPackageLocationTo() {
        return packageLocationTo;
    }

    public void setPackageLocationTo(String packageLocationTo) {
        this.packageLocationTo = packageLocationTo;
    }

    public String getPackageLocationRoute() {
        return packageLocationRoute;
    }

    public void setPackageLocationRoute(String packageLocationRoute) {
        this.packageLocationRoute = packageLocationRoute;
    }

    public String getPackageAvailableStartDate() {
        return packageAvailableStartDate;
    }

    public void setPackageAvailableStartDate(String packageAvailableStartDate) {
        this.packageAvailableStartDate = packageAvailableStartDate;
    }

    public String getPackageAvailableEndDate() {
        return packageAvailableEndDate;
    }

    public void setPackageAvailableEndDate(String packageAvailableEndDate) {
        this.packageAvailableEndDate = packageAvailableEndDate;
    }

    public String getPackageOverview() {
        return packageOverview;
    }

    public void setPackageOverview(String packageOverview) {
        this.packageOverview = packageOverview;
    }

    public String getPackageCancellationPolicy() {
        return packageCancellationPolicy;
    }

    public void setPackageCancellationPolicy(String packageCancellationPolicy) {
        this.packageCancellationPolicy = packageCancellationPolicy;
    }

    public String getPackageRemarks() {
        return packageRemarks;
    }

    public void setPackageRemarks(String packageRemarks) {
        this.packageRemarks = packageRemarks;
    }

    public String getPackageActivities() {
        return packageActivities;
    }

    public void setPackageActivities(String packageActivities) {
        this.packageActivities = packageActivities;
    }

    public String getPackageDo() {
        return packageDo;
    }

    public void setPackageDo(String packageDo) {
        this.packageDo = packageDo;
    }

    public String getPackageDont() {
        return packageDont;
    }

    public void setPackageDont(String packageDont) {
        this.packageDont = packageDont;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageDiscount() {
        return packageDiscount;
    }

    public void setPackageDiscount(String packageDiscount) {
        this.packageDiscount = packageDiscount;
    }

    public Object getPackageNameBn() {
        return packageNameBn;
    }

    public void setPackageNameBn(Object packageNameBn) {
        this.packageNameBn = packageNameBn;
    }

    public Object getPackageLocationRouteBn() {
        return packageLocationRouteBn;
    }

    public void setPackageLocationRouteBn(Object packageLocationRouteBn) {
        this.packageLocationRouteBn = packageLocationRouteBn;
    }

    public Object getPackageOverviewBn() {
        return packageOverviewBn;
    }

    public void setPackageOverviewBn(Object packageOverviewBn) {
        this.packageOverviewBn = packageOverviewBn;
    }

    public Object getPackageCancellationPolicyBn() {
        return packageCancellationPolicyBn;
    }

    public void setPackageCancellationPolicyBn(Object packageCancellationPolicyBn) {
        this.packageCancellationPolicyBn = packageCancellationPolicyBn;
    }

    public Object getPackageRemarksBn() {
        return packageRemarksBn;
    }

    public void setPackageRemarksBn(Object packageRemarksBn) {
        this.packageRemarksBn = packageRemarksBn;
    }

    public Object getPackageActivitiesBn() {
        return packageActivitiesBn;
    }

    public void setPackageActivitiesBn(Object packageActivitiesBn) {
        this.packageActivitiesBn = packageActivitiesBn;
    }

    public Object getPackageDoBn() {
        return packageDoBn;
    }

    public void setPackageDoBn(Object packageDoBn) {
        this.packageDoBn = packageDoBn;
    }

    public Object getPackageDontBn() {
        return packageDontBn;
    }

    public void setPackageDontBn(Object packageDontBn) {
        this.packageDontBn = packageDontBn;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
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

    public String getProviderNameBn() {
        return providerNameBn;
    }

    public void setProviderNameBn(String providerNameBn) {
        this.providerNameBn = providerNameBn;
    }

    public String getProviderAddressBn() {
        return providerAddressBn;
    }

    public void setProviderAddressBn(String providerAddressBn) {
        this.providerAddressBn = providerAddressBn;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationDivisionId() {
        return destinationDivisionId;
    }

    public void setDestinationDivisionId(String destinationDivisionId) {
        this.destinationDivisionId = destinationDivisionId;
    }

    public String getDestinationDistrictId() {
        return destinationDistrictId;
    }

    public void setDestinationDistrictId(String destinationDistrictId) {
        this.destinationDistrictId = destinationDistrictId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationNameBn() {
        return destinationNameBn;
    }

    public void setDestinationNameBn(String destinationNameBn) {
        this.destinationNameBn = destinationNameBn;
    }

    public String getDestinationDetails() {
        return destinationDetails;
    }

    public void setDestinationDetails(String destinationDetails) {
        this.destinationDetails = destinationDetails;
    }

    public String getDestinationImage() {
        return destinationImage;
    }

    public void setDestinationImage(String destinationImage) {
        this.destinationImage = destinationImage;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public String getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(String destinationLong) {
        this.destinationLong = destinationLong;
    }

    public Object getDestinationTags() {
        return destinationTags;
    }

    public void setDestinationTags(Object destinationTags) {
        this.destinationTags = destinationTags;
    }

    public String getDestinationStatus() {
        return destinationStatus;
    }

    public void setDestinationStatus(String destinationStatus) {
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

}