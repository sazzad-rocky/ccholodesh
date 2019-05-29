/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

/**
 * Created by rhythmshahriar on 11/2/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class CustomTripPlanRouteModel {

    @SerializedName("route_from")
    @Expose
    private Integer routeFrom;
    @SerializedName("route_to")
    @Expose
    private Integer routeTo;

    public Integer getRouteFrom() {
        return routeFrom;
    }

    public void setRouteFrom(Integer routeFrom) {
        this.routeFrom = routeFrom;
    }

    public Integer getRouteTo() {
        return routeTo;
    }

    public void setRouteTo(Integer routeTo) {
        this.routeTo = routeTo;
    }

}
