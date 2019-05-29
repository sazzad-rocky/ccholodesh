/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

/**
 * Created by rhythmshahriar on 11/4/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomTripPlanNewRouteGetModel {


    @SerializedName("direct_district_name_from")
    @Expose
    private String directDistrictNameFrom;

    @SerializedName("direct_district_name_from_id")
    @Expose
    private String directDistrictNameFromId;
    @SerializedName("direct_district_name_to")
    @Expose
    private String directDistrictNameTo;
    @SerializedName("direct_district_name_to_id")
    @Expose
    private String directDistrictNameToId;
    @SerializedName("route_name")
    @Expose
    private String routeName;
    @SerializedName("route_id")
    @Expose
    private Integer routeId;
    @SerializedName("start_district_name")
    @Expose
    private String startDistrictName;
    @SerializedName("start_district_id")
    @Expose
    private String startDistrictId;
    @SerializedName("end_district_name")
    @Expose
    private String endDistrictName;
    @SerializedName("end_district_id")
    @Expose
    private String endDistrictId;

    public String getDirectDistrictNameFrom() {
        return directDistrictNameFrom;
    }

    public void setDirectDistrictNameFrom(String directDistrictNameFrom) {
        this.directDistrictNameFrom = directDistrictNameFrom;
    }

    public String getDirectDistrictNameFromId() {
        return directDistrictNameFromId;
    }

    public void setDirectDistrictNameFromId(String directDistrictNameFromId) {
        this.directDistrictNameFromId = directDistrictNameFromId;
    }

    public String getDirectDistrictNameTo() {
        return directDistrictNameTo;
    }

    public void setDirectDistrictNameTo(String directDistrictNameTo) {
        this.directDistrictNameTo = directDistrictNameTo;
    }

    public String getDirectDistrictNameToId() {
        return directDistrictNameToId;
    }

    public void setDirectDistrictNameToId(String directDistrictNameToId) {
        this.directDistrictNameToId = directDistrictNameToId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getStartDistrictName() {
        return startDistrictName;
    }

    public void setStartDistrictName(String startDistrictName) {
        this.startDistrictName = startDistrictName;
    }

    public String getStartDistrictId() {
        return startDistrictId;
    }

    public void setStartDistrictId(String startDistrictId) {
        this.startDistrictId = startDistrictId;
    }

    public String getEndDistrictName() {
        return endDistrictName;
    }

    public void setEndDistrictName(String endDistrictName) {
        this.endDistrictName = endDistrictName;
    }

    public String getEndDistrictId() {
        return endDistrictId;
    }

    public void setEndDistrictId(String endDistrictId) {
        this.endDistrictId = endDistrictId;
    }
}
