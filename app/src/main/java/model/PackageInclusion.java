package model;

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

public class PackageInclusion {

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
    @SerializedName("package_inclusion_id")
    @Expose
    private Integer packageInclusionId;
    @SerializedName("package_inclusion_name")
    @Expose
    private String packageInclusionName;
    @SerializedName("package_inclusion_image")
    @Expose
    private String packageInclusionImage;

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

    public Integer getPackageInclusionId() {
        return packageInclusionId;
    }

    public void setPackageInclusionId(Integer packageInclusionId) {
        this.packageInclusionId = packageInclusionId;
    }

    public String getPackageInclusionName() {
        return packageInclusionName;
    }

    public void setPackageInclusionName(String packageInclusionName) {
        this.packageInclusionName = packageInclusionName;
    }

    public String getPackageInclusionImage() {
        return packageInclusionImage;
    }

    public void setPackageInclusionImage(String packageInclusionImage) {
        this.packageInclusionImage = packageInclusionImage;
    }

}