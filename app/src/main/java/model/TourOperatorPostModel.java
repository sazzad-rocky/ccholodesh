/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

/**
 * Created by rhythmshahriar on 21/1/18.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourOperatorPostModel {

    @SerializedName("tailormade_id")
    @Expose
    private String tailormadeId;
    @SerializedName("tailormade_customer_id")
    @Expose
    private String tailormadeCustomerId;
    @SerializedName("tour_operator_status")
    @Expose
    private String tourOperatorStatus;
    @SerializedName("tailormade_status")
    @Expose
    private String tailormadeStatus;
    @SerializedName("tour_operator")
    @Expose
    private List<TourOperator> tourOperator = null;

    public String getTailormadeId() {
        return tailormadeId;
    }

    public void setTailormadeId(String tailormadeId) {
        this.tailormadeId = tailormadeId;
    }

    public String getTailormadeCustomerId() {
        return tailormadeCustomerId;
    }

    public void setTailormadeCustomerId(String tailormadeCustomerId) {
        this.tailormadeCustomerId = tailormadeCustomerId;
    }

    public String getTourOperatorStatus() {
        return tourOperatorStatus;
    }

    public void setTourOperatorStatus(String tourOperatorStatus) {
        this.tourOperatorStatus = tourOperatorStatus;
    }

    public String getTailormadeStatus() {
        return tailormadeStatus;
    }

    public void setTailormadeStatus(String tailormadeStatus) {
        this.tailormadeStatus = tailormadeStatus;
    }

    public List<TourOperator> getTourOperator() {
        return tourOperator;
    }

    public void setTourOperator(List<TourOperator> tourOperator) {
        this.tourOperator = tourOperator;
    }

}

