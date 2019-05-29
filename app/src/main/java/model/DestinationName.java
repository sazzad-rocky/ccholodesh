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

import java.util.ArrayList;

import helpers.BaseURL;

/**
 * Created by rhythmshahriar on 7/18/17.
 */

public class DestinationName {

    @SerializedName("destination_id")
    @Expose
    private Integer destinationId;

    @SerializedName("destination_name")
    @Expose
    private String destinationName;

    @SerializedName("destination_name_bn")
    @Expose
    private String destinationNameBn;

    public Integer getDestinationId() {
        return destinationId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getDestinationNameBn() {
        return destinationNameBn;
    }

    @Override
    public String toString() {
        if (BaseURL.LANGUAGE_ENG)
        return destinationName;
        else return destinationNameBn;
    }
}
