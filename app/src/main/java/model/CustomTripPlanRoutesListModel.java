/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by rhythmshahriar on 11/2/17.
 */

public class CustomTripPlanRoutesListModel {
    @SerializedName("routeList")
    @Expose
    private List<CustomTripPlanRouteModel> routeList = null;

    public List<CustomTripPlanRouteModel> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<CustomTripPlanRouteModel> routeList) {
        this.routeList = routeList;
    }
}
