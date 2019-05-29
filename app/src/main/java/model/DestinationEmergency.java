/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rhythmshahriar on 7/18/17.
 */

public class DestinationEmergency {

    @SerializedName("destination_emergency_id")
    @Expose
    private Integer destinationEmergencyId;
    @SerializedName("destination_emergency_destination_id")
    @Expose
    private String destinationEmergencyDestinationId;
    @SerializedName("destination_emergency_emergency_type_id")
    @Expose
    private String destinationEmergencyEmergencyTypeId;
    @SerializedName("destination_emergency_org_name")
    @Expose
    private String destinationEmergencyOrgName;
    @SerializedName("destination_emergency_org_name_bn")
    @Expose
    private String destinationEmergencyOrgNameBn;
    @SerializedName("destination_emergency_address")
    @Expose
    private String destinationEmergencyAddress;
    @SerializedName("destination_emergency_contact_info")
    @Expose
    private String destinationEmergencyContactInfo;
    @SerializedName("destination_emergency_hotline")
    @Expose
    private String destinationEmergencyHotline;
    @SerializedName("destination_emergency_address_bn")
    @Expose
    private String destinationEmergencyAddressBn;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("emergency_type_id")
    @Expose
    private String emergencyTypeId;
    @SerializedName("emergency_type_name")
    @Expose
    private String emergencyTypeName;
    @SerializedName("emergency_type_name_bn")
    @Expose
    private String emergencyTypeNameBn;
    @SerializedName("emergency_type_status")
    @Expose
    private String emergencyTypeStatus;

    public Integer getDestinationEmergencyId() {
        return destinationEmergencyId;
    }

    public void setDestinationEmergencyId(Integer destinationEmergencyId) {
        this.destinationEmergencyId = destinationEmergencyId;
    }

    public String getDestinationEmergencyDestinationId() {
        return destinationEmergencyDestinationId;
    }

    public void setDestinationEmergencyDestinationId(String destinationEmergencyDestinationId) {
        this.destinationEmergencyDestinationId = destinationEmergencyDestinationId;
    }

    public String getDestinationEmergencyEmergencyTypeId() {
        return destinationEmergencyEmergencyTypeId;
    }

    public void setDestinationEmergencyEmergencyTypeId(String destinationEmergencyEmergencyTypeId) {
        this.destinationEmergencyEmergencyTypeId = destinationEmergencyEmergencyTypeId;
    }

    public String getDestinationEmergencyOrgName() {
        return destinationEmergencyOrgName;
    }

    public void setDestinationEmergencyOrgName(String destinationEmergencyOrgName) {
        this.destinationEmergencyOrgName = destinationEmergencyOrgName;
    }

    public String getDestinationEmergencyOrgNameBn() {
        return destinationEmergencyOrgNameBn;
    }

    public void setDestinationEmergencyOrgNameBn(String destinationEmergencyOrgNameBn) {
        this.destinationEmergencyOrgNameBn = destinationEmergencyOrgNameBn;
    }

    public String getDestinationEmergencyAddress() {
        return destinationEmergencyAddress;
    }

    public void setDestinationEmergencyAddress(String destinationEmergencyAddress) {
        this.destinationEmergencyAddress = destinationEmergencyAddress;
    }

    public String getDestinationEmergencyContactInfo() {
        return destinationEmergencyContactInfo;
    }

    public void setDestinationEmergencyContactInfo(String destinationEmergencyContactInfo) {
        this.destinationEmergencyContactInfo = destinationEmergencyContactInfo;
    }

    public String getDestinationEmergencyHotline() {
        return destinationEmergencyHotline;
    }

    public void setDestinationEmergencyHotline(String destinationEmergencyHotline) {
        this.destinationEmergencyHotline = destinationEmergencyHotline;
    }

    public String getDestinationEmergencyAddressBn() {
        return destinationEmergencyAddressBn;
    }

    public void setDestinationEmergencyAddressBn(String destinationEmergencyAddressBn) {
        this.destinationEmergencyAddressBn = destinationEmergencyAddressBn;
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

    public String getEmergencyTypeId() {
        return emergencyTypeId;
    }

    public void setEmergencyTypeId(String emergencyTypeId) {
        this.emergencyTypeId = emergencyTypeId;
    }

    public String getEmergencyTypeName() {
        return emergencyTypeName;
    }

    public void setEmergencyTypeName(String emergencyTypeName) {
        this.emergencyTypeName = emergencyTypeName;
    }

    @Override
    public String toString() {
        return destinationEmergencyOrgName;
    }

    public String getEmergencyTypeNameBn() {
        return emergencyTypeNameBn;
    }

    public void setEmergencyTypeNameBn(String emergencyTypeNameBn) {
        this.emergencyTypeNameBn = emergencyTypeNameBn;
    }

    public String getEmergencyTypeStatus() {
        return emergencyTypeStatus;
    }

    public void setEmergencyTypeStatus(String emergencyTypeStatus) {
        this.emergencyTypeStatus = emergencyTypeStatus;
    }

}
