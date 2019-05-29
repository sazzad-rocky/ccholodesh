/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

/**
 * Created by rhythmshahriar on 21/1/18.
 */

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import helpers.BaseURL;

public class TourOperatorModel  implements Comparable< TourOperatorModel >{

    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("provider_user_id")
    @Expose
    private String providerUserId;
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
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public boolean isSelected;

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
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

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }



    @Override
    public int compareTo(@NonNull TourOperatorModel o) {

        if (!BaseURL.LANGUAGE_ENG) {
            return this.getProviderNameBn().compareTo(o.getProviderAddressBn());
        }
        return this.getProviderName().compareTo(o.getProviderName());
    }
}



