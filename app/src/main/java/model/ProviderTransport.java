package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class ProviderTransport {
    @SerializedName("route_id")
    @Expose
    private Integer routeId;
    @SerializedName("route_name")
    @Expose
    private String routeName;
    @SerializedName("tt_id")
    @Expose
    private Integer ttId;
    @SerializedName("tt_name")
    @Expose
    private String ttName;
    @SerializedName("transport_info_id")
    @Expose
    private Integer transportInfoId;
    @SerializedName("transport_info_operator_name")
    @Expose
    private String transportInfoOperatorName;
    @SerializedName("transport_info_estimated_time")
    @Expose
    private String transportInfoEstimatedTime;
    @SerializedName("transport_info_price")
    @Expose
    private String transportInfoPrice;


    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getTtId() {
        return ttId;
    }

    public void setTtId(Integer ttId) {
        this.ttId = ttId;
    }

    public String getTtName() {
        return ttName;
    }

    public void setTtName(String ttName) {
        this.ttName = ttName;
    }

    public Integer getTransportInfoId() {
        return transportInfoId;
    }

    public void setTransportInfoId(Integer transportInfoId) {
        this.transportInfoId = transportInfoId;
    }

    public String getTransportInfoOperatorName() {
        return transportInfoOperatorName;
    }

    public void setTransportInfoOperatorName(String transportInfoOperatorName) {
        this.transportInfoOperatorName = transportInfoOperatorName;
    }

    public String getTransportInfoEstimatedTime() {
        return transportInfoEstimatedTime;
    }

    public void setTransportInfoEstimatedTime(String transportInfoEstimatedTime) {
        this.transportInfoEstimatedTime = transportInfoEstimatedTime;
    }

    public String getTransportInfoPrice() {
        return transportInfoPrice;
    }

    public void setTransportInfoPrice(String transportInfoPrice) {
        this.transportInfoPrice = transportInfoPrice;
    }

}