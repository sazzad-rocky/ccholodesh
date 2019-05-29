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

import helpers.BaseURL;

/**
 * Created by rhythmshahriar on 7/18/17.
 */

public class RouteNew {
    @SerializedName("route_id")
    @Expose
    private Integer routeId;

    @SerializedName("route_from")
    @Expose
    private Integer routeFromId;

    @SerializedName("route_to")
    @Expose
    private Integer routeToId;

    @SerializedName("route_name")
    @Expose
    private String routeName;

    @SerializedName("route_details")
    @Expose
    private String routeDetails;

    @SerializedName("route_status")
    @Expose
    private String routeStatus;

    @SerializedName("route_name_bn")
    @Expose
    private String routeNameBn;

    @SerializedName("route_details_bn")
    @Expose
    private String routeDetailsBn;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @Override
    public String toString() {

        if (!BaseURL.LANGUAGE_ENG && this.routeNameBn != null)return this.routeNameBn;
        else return this.routeName;
    }

    public String getRouteName (){return this.routeName;}

    public Integer getRouteFromId() {
        return routeFromId;
    }

    public Integer getRouteId () {return routeId;}
}
