package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.zxing.common.BitSource;

import helpers.BaseURL;
import io.realm.RealmModel;
import io.realm.RealmObject;

public class Route extends RealmObject{
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
    private Integer startDistrictId;
    @SerializedName("end_district_name")
    @Expose
    private String endDistrictName;
    @SerializedName("end_district_id")
    @Expose
    private Integer endDistrictId;
    @SerializedName("route_transport")
    private TransportProvider transportProvider;

    @SerializedName("route_name_bn")
    private String routeNameBn;

    @SerializedName("start_district_name_bn")
    private String startDistrictNameBn;

    @SerializedName("end_district_name_bn")
    private String endDistrictNameBn;

    public TransportProvider getTransportProvider() {
        return transportProvider;
    }

    public Route setTransportProvider(TransportProvider transportProvider) {
        this.transportProvider = transportProvider;
        return this;
    }

    public String getRouteName() {
        if (!BaseURL.LANGUAGE_ENG){

            return routeNameBn;
        }
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
        if (!BaseURL.LANGUAGE_ENG){

            return startDistrictNameBn;
        }
        return startDistrictName;
    }

    public void setStartDistrictName(String startDistrictName) {

        this.startDistrictName = startDistrictName;
    }

    public Integer getStartDistrictId() {
        return startDistrictId;
    }

    public void setStartDistrictId(Integer startDistrictId) {
        this.startDistrictId = startDistrictId;
    }

    public String getEndDistrictName() {
        if (!BaseURL.LANGUAGE_ENG){

            return endDistrictNameBn == null ? endDistrictName : endDistrictNameBn;
        }
        return endDistrictName;
    }

    public void setEndDistrictName(String endDistrictName) {
        this.endDistrictName = endDistrictName;
    }

    public Integer getEndDistrictId() {
        return endDistrictId;
    }

    public void setEndDistrictId(Integer endDistrictId) {
        this.endDistrictId = endDistrictId;
    }

    @Override
    public String toString() {
        return endDistrictName;
    }
}