/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;
/**
 * Created by rhythmshahriar on 9/13/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TailorMadeTransport {
    @SerializedName("tailormade_route_id")
    @Expose
    private Integer tailormadeRouteId;
    @SerializedName("start_district_id")
    @Expose
    private String startDistrictId;
    @SerializedName("start_district_name")
    @Expose
    private String startDistrictName;
    @SerializedName("end_district_id")
    @Expose
    private String endDistrictId;
    @SerializedName("end_district_name")
    @Expose
    private String endDistrictName;
    @SerializedName("route_id")
    @Expose
    private String routeId;
    @SerializedName("route_name")
    @Expose
    private String routeName;
    @SerializedName("tailormade_route_route_name")
    @Expose
    private String tailormadeRouteRouteName;
    @SerializedName("tailormade_route_tailormade_id")
    @Expose
    private String tailormadeRouteTailormadeId;
    @SerializedName("tailormade_route_route_id")
    @Expose
    private String tailormadeRouteRouteId;
    @SerializedName("tailormade_route_transport_id")
    @Expose
    private String tailormadeRouteTransportId;
    @SerializedName("tailormade_route_transport_name")
    @Expose
    private String tailormadeRouteTransportName;
    @SerializedName("tailormade_route_transport_info_id")
    @Expose
    private String tailormadeRouteTransportInfoId;
    @SerializedName("tailormade_route_transport_info_operator_name")
    @Expose
    private String tailormadeRouteTransportInfoOperatorName;
    @SerializedName("tailormade_route_transport_info_estimated_time")
    @Expose
    private String tailormadeRouteTransportInfoEstimatedTime;
    @SerializedName("tailormade_route_transport_info_price")
    @Expose
    private String tailormadeRouteTransportInfoPrice;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("transport_date")
    @Expose
    private String transport_date;

    public String gettransport_date() {
        return transport_date;
    }

    public Integer getTailormadeRouteId() {
        return tailormadeRouteId;
    }

    public void setTailormadeRouteId(Integer tailormadeRouteId) {
        this.tailormadeRouteId = tailormadeRouteId;
    }

    public String getStartDistrictId() {
        return startDistrictId;
    }

    public void setStartDistrictId(String startDistrictId) {
        this.startDistrictId = startDistrictId;
    }

    public String getStartDistrictName() {
        return startDistrictName;
    }

    public void setStartDistrictName(String startDistrictName) {
        this.startDistrictName = startDistrictName;
    }

    public String getEndDistrictId() {
        return endDistrictId;
    }

    public void setEndDistrictId(String endDistrictId) {
        this.endDistrictId = endDistrictId;
    }

    public String getEndDistrictName() {
        return endDistrictName;
    }

    public void setEndDistrictName(String endDistrictName) {
        this.endDistrictName = endDistrictName;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getTailormadeRouteRouteName() {
        return tailormadeRouteRouteName;
    }

    public void setTailormadeRouteRouteName(String tailormadeRouteRouteName) {
        this.tailormadeRouteRouteName = tailormadeRouteRouteName;
    }

    public String getTailormadeRouteTailormadeId() {
        return tailormadeRouteTailormadeId;
    }

    public void setTailormadeRouteTailormadeId(String tailormadeRouteTailormadeId) {
        this.tailormadeRouteTailormadeId = tailormadeRouteTailormadeId;
    }

    public String getTailormadeRouteRouteId() {
        return tailormadeRouteRouteId;
    }

    public void setTailormadeRouteRouteId(String tailormadeRouteRouteId) {
        this.tailormadeRouteRouteId = tailormadeRouteRouteId;
    }

    public String getTailormadeRouteTransportId() {
        return tailormadeRouteTransportId;
    }

    public void setTailormadeRouteTransportId(String tailormadeRouteTransportId) {
        this.tailormadeRouteTransportId = tailormadeRouteTransportId;
    }

    public String getTailormadeRouteTransportName() {
        return tailormadeRouteTransportName;
    }

    public void setTailormadeRouteTransportName(String tailormadeRouteTransportName) {
        this.tailormadeRouteTransportName = tailormadeRouteTransportName;
    }

    public String getTailormadeRouteTransportInfoId() {
        return tailormadeRouteTransportInfoId;
    }

    public void setTailormadeRouteTransportInfoId(String tailormadeRouteTransportInfoId) {
        this.tailormadeRouteTransportInfoId = tailormadeRouteTransportInfoId;
    }

    public String getTailormadeRouteTransportInfoOperatorName() {
        return tailormadeRouteTransportInfoOperatorName;
    }

    public void setTailormadeRouteTransportInfoOperatorName(String tailormadeRouteTransportInfoOperatorName) {
        this.tailormadeRouteTransportInfoOperatorName = tailormadeRouteTransportInfoOperatorName;
    }

    public String getTailormadeRouteTransportInfoEstimatedTime() {
        return tailormadeRouteTransportInfoEstimatedTime;
    }

    public void setTailormadeRouteTransportInfoEstimatedTime(String tailormadeRouteTransportInfoEstimatedTime) {
        this.tailormadeRouteTransportInfoEstimatedTime = tailormadeRouteTransportInfoEstimatedTime;
    }

    public String getTailormadeRouteTransportInfoPrice() {
        return tailormadeRouteTransportInfoPrice;
    }

    public void setTailormadeRouteTransportInfoPrice(String tailormadeRouteTransportInfoPrice) {
        this.tailormadeRouteTransportInfoPrice = tailormadeRouteTransportInfoPrice;
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

}