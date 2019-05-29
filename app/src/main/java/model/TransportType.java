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

public class TransportType {

    @SerializedName("transport_info_route_id")
    @Expose
    private Integer transportRouteId;

    @SerializedName("tt_id")
    @Expose
    private Integer transportTypeId;

    @SerializedName("tt_name")
    @Expose
    private String transportTypeName;

    @SerializedName("tt_name_bn")
    @Expose
    private String transportTypeNameBn;

    @SerializedName("tt_status")
    @Expose
    private String transportTypeStatus;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public TransportType ()
    {
        transportTypeName = "N/A";
        transportTypeId = 0;
        transportTypeNameBn = "পাওয়া যায় নি";
    }

    @Override
    public String toString() {

        if (BaseURL.LANGUAGE_ENG)return transportTypeName;
        else return transportTypeNameBn;
    }
    public void setTypeName (String name)
    {
        transportTypeName = name;
    }
    public Integer getTransportTypeId ()
    {
        return transportTypeId;
    }
}
