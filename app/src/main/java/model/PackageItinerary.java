package model;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class PackageItinerary {

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
    @SerializedName("package_id")
    @Expose
    private Integer packageId;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("package_itinerary_id")
    @Expose
    private Integer packageItineraryId;
    @SerializedName("package_start_time")
    @Expose
    private String packageStartTime;
    @SerializedName("package_end_time")
    @Expose
    private String packageEndTime;
    @SerializedName("package_details")
    @Expose
    private String packageDetails;
    @SerializedName("package_itinerary_date")
    @Expose
    private String packageItineraryDate;
    @SerializedName("package_itinerary_place")
    @Expose
    private String packageItineraryPlace;
    @SerializedName("package_itinerary_transport")
    @Expose
    private String packageItineraryTransport;
    @SerializedName("package_itinerary_meal")
    @Expose
    private String packageItineraryMeal;
    @SerializedName("package_itinerary_schedule")
    @Expose
    private String packageItinerarySchedule;

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

    public Integer getPackageItineraryId() {
        return packageItineraryId;
    }

    public void setPackageItineraryId(Integer packageItineraryId) {
        this.packageItineraryId = packageItineraryId;
    }

    public String getPackageStartTime() {
        return packageStartTime;
    }

    public void setPackageStartTime(String packageStartTime) {
        this.packageStartTime = packageStartTime;
    }

    public String getPackageEndTime() {
        return packageEndTime;
    }

    public void setPackageEndTime(String packageEndTime) {
        this.packageEndTime = packageEndTime;
    }

    public String getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(String packageDetails) {
        this.packageDetails = packageDetails;
    }

    public String getPackageItineraryDate() {
        return packageItineraryDate;
    }

    public void setPackageItineraryDate(String packageItineraryDate) {
        this.packageItineraryDate = packageItineraryDate;
    }

    public String getPackageItineraryPlace() {
        return packageItineraryPlace;
    }

    public void setPackageItineraryPlace(String packageItineraryPlace) {
        this.packageItineraryPlace = packageItineraryPlace;
    }

    public String getPackageItineraryTransport() {
        return packageItineraryTransport;
    }

    public void setPackageItineraryTransport(String packageItineraryTransport) {
        this.packageItineraryTransport = packageItineraryTransport;
    }

    public String getPackageItineraryMeal() {
        return packageItineraryMeal;
    }

    public void setPackageItineraryMeal(String packageItineraryMeal) {
        this.packageItineraryMeal = packageItineraryMeal;
    }

    public String getPackageItinerarySchedule() {
        return packageItinerarySchedule;
    }

    public void setPackageItinerarySchedule(String packageItinerarySchedule) {
        this.packageItinerarySchedule = packageItinerarySchedule;
    }

}