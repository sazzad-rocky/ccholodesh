package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class PackageDetails extends RealmObject {

    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("provider_email")
    @Expose
    private String providerEmail;
    @SerializedName("provider_hotline")
    @Expose
    private String providerHotline;
    @SerializedName("provider_address")
    @Expose
    private String providerAddress;
    @SerializedName("provider_phone")
    @Expose
    private String providerPhone;
    @SerializedName("destination_id")
    @Expose
    private Integer destinationId;
    @SerializedName("destination_name")
    @Expose
    private String destinationName;
    @SerializedName("package_id")
    @Expose
    private Integer packageId;
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
    @SerializedName("package_price")
    @Expose
    private String packagePrice;
    @SerializedName("package_location_route")
    @Expose
    private String packageLocationRoute;
    @SerializedName("package_discount")
    @Expose
    private String packageDiscount;
    @SerializedName("package_available_start_date")
    @Expose
    private String packageAvailableStartDate;
    @SerializedName("package_available_end_date")
    @Expose
    private String packageAvailableEndDate;
    @SerializedName("package_overview")
    @Expose
    private String packageOverview;
    @SerializedName("package_activities")
    @Expose
    private String packageActivities;
    @SerializedName("package_remarks")
    @Expose
    private String packageRemarks;


    @SerializedName("package_code")
    @Expose
    public String packageCode;

    @SerializedName("package_discount_price")
    @Expose
    public String discountPrice;

    @SerializedName("package_do_and_dont")
    @Expose
    public String dodont;
    @SerializedName("package_do_and_dont_bn")
    @Expose
    public String dodontbn;
    @SerializedName("package_cancellation_policy")
    @Expose
    public String cancellationPolicy;
    @SerializedName("package_cancellation_policy_bn")
    @Expose
    public String cancellationPolicyBn;
    @SerializedName("package_itinerary")
    @Expose
    public String packageItinerary;
    @SerializedName("package_itinerary_bn")
    @Expose
    public String packageItineraryBn;
    @SerializedName("package_inclusion")
    @Expose
    public String packageIncluion;
    @SerializedName("package_inclusion_bn")
    @Expose
    public String packageIncluionBn;
    @SerializedName("provider_name_bn")
    @Expose
    public String providerNameBn;
    @SerializedName("destination_name_bn")
    @Expose
    public String destNameBn;
    @SerializedName("package_name_bn")
    @Expose
    public String pckgNameBn;
    @SerializedName("package_location_from_bn")
    @Expose
    public String pckgLocfromBn;

    @SerializedName("package_location_to_bn")
    @Expose
    public String pckgLocToBn;
    @SerializedName("package_location_route_bn")
    @Expose
    public String routeBn;
    @SerializedName("package_overview_bn")
    @Expose
    public String overViewBn;
    @SerializedName("package_activities_bn")
    @Expose
    public String activitiesBn;
    @SerializedName("package_remarks_bn")
    @Expose
    public String remarksBn;
    @SerializedName("provider_address_bn")
    @Expose
    public String addressBn;

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

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getProviderHotline() {
        return providerHotline;
    }

    public void setProviderHotline(String providerHotline) {
        this.providerHotline = providerHotline;
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

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
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

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageLocationRoute() {
        return packageLocationRoute;
    }

    public void setPackageLocationRoute(String packageLocationRoute) {
        this.packageLocationRoute = packageLocationRoute;
    }

    public String getPackageDiscount() {
        return packageDiscount;
    }

    public void setPackageDiscount(String packageDiscount) {
        this.packageDiscount = packageDiscount;
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

    public String getPackageActivities() {
        return packageActivities;
    }

    public void setPackageActivities(String packageActivities) {
        this.packageActivities = packageActivities;
    }

    public String getPackageRemarks() {
        return packageRemarks;
    }

    public void setPackageRemarks(String packageRemarks) {
        this.packageRemarks = packageRemarks;
    }

}