package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import helpers.BaseURL;

public class Place {

    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("division_id")
    @Expose
    private Integer divisionId;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("district_name_bn")
    @Expose
    private String districtNameBn;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtNameBn = districtName;
    }
    public String getDistrictNameBn() {
        return districtNameBn;
    }

    public void setDistrictNameBn(String districtName) {
        this.districtNameBn = districtName;
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

    @Override
    public String toString() {

        if (BaseURL.LANGUAGE_ENG)return districtName;
        else return districtNameBn;
    }
}