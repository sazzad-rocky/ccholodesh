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

public class TransportInfo {

    @SerializedName("transport_info_id")
    @Expose
    private Integer transportInfoId;

    @SerializedName("transport_info_route_id")
    @Expose
    private Integer transportInfoRouteId;

    @SerializedName("transport_info_transport_type_id")
    @Expose
    private Integer transportTypeid;

    @SerializedName("transport_info_operator_name")
    @Expose
    private String transportOperatorName;

    @SerializedName("transport_info_trips")
    @Expose
    private String NoOfTrips;

    @SerializedName("transport_info_first_trip")
    @Expose
    private String transportFirstTrip;

    @SerializedName("transport_info_last_trip")
    @Expose
    private String transportLaststTrip;

    @SerializedName("transport_info_operator_type")
    @Expose
    private String transportOperatorType;

    @SerializedName("transport_info_estimated_time")
    @Expose
    private String transportEstimatedTime;

    @SerializedName("transport_info_price")
    @Expose
    private String transportPrice;

    @SerializedName("transport_info_operator_name_bn")
    @Expose
    private String getTransportOperatorNameBn;

    @SerializedName("transport_info_operator_type_bn")
    @Expose
    private String getTransportOperatorTypeBn;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("route_info_id")
    @Expose
    private Integer routeInfoId;

    @SerializedName("route_info_route_id")
    @Expose
    private Integer routeInfoRouteId;

    @SerializedName("route_info_start_id")
    @Expose
    private Integer routeInfoStartId;

    @SerializedName("route_info_end_id")
    @Expose
    private Integer routeInfoEndId;

    @SerializedName("route_info_name")
    @Expose
    private String routeInfoName;

    @SerializedName("route_info_name_bn")
    @Expose
    private String routeInfoNameBn;

    public TransportInfo()
    {

        routeInfoName = "N/A";
        routeInfoNameBn = "পাওয়া যায় নি";
        getTransportOperatorTypeBn = "পাওয়া যায় নি";
        getTransportOperatorNameBn = "পাওয়া যায় নি";
        transportEstimatedTime = "N/A";
        transportOperatorType = "N/A";
        transportLaststTrip = "N/A";
        transportOperatorName = "N/A";
        transportFirstTrip = "N/A";
        NoOfTrips = "N/A";
        transportInfoId = 0;
        transportInfoRouteId = 0;
        routeInfoEndId = 0;
        routeInfoStartId = 0;
        routeInfoRouteId = 0;

        routeInfoId = 0;
        transportTypeid = 0;





    }

    public Integer getTransportInfoId() {
        return transportInfoId;
    }

    public Integer getTransportInfoRouteId() {
        return transportInfoRouteId;
    }

    public Integer getTransportTypeid() {
        return transportTypeid;
    }

    public String getTransportOperatorName() {
        return transportOperatorName;
    }

    public String getNoOfTrips() {
        return NoOfTrips;
    }

    public String getTransportFirstTrip() {
        return transportFirstTrip;
    }

    public String getTransportLaststTrip() {
        return transportLaststTrip;
    }

    public String getTransportOperatorType() {
        return transportOperatorType;
    }

    public String getTransportEstimatedTime() {
        return transportEstimatedTime;
    }

    public String getTransportPrice() {
        return transportPrice;
    }

    public String getGetTransportOperatorNameBn() {
        return getTransportOperatorNameBn;
    }

    public String getGetTransportOperatorTypeBn() {
        return getTransportOperatorTypeBn;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Integer getRouteInfoId() {
        return routeInfoId;
    }

    public Integer getRouteInfoRouteId() {
        return routeInfoRouteId;
    }

    public Integer getRouteInfoStartId() {
        return routeInfoStartId;
    }

    public Integer getRouteInfoEndId() {
        return routeInfoEndId;
    }

    public String getRouteInfoName() {
        return routeInfoName;
    }

    public String getRouteInfoNameBn() {
        return routeInfoNameBn;
    }
























}
