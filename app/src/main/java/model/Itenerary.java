package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by Olivine on 5/10/2017.
 */

public class Itenerary extends RealmObject {



    @SerializedName("local_tour_id")
    @Expose
    private Integer localTourId;
    @SerializedName("dayplan")
    @Expose
    private String dayplan;
    @SerializedName("placeName")
    @Expose
    private String placeName;
    @SerializedName("perPersonCost")
    @Expose
    private String perPersonCost;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("local_tour_transport_type")
    @Expose
    private String transport;
    public float lattitude;
    public float longitude;



    public Integer getLocalTourId() {
        return localTourId;
    }

    public Itenerary setLocalTourId(Integer localTourId) {
        this.localTourId = localTourId;
        return this;
    }

    public String getDayplan() {
        return dayplan;
    }

    public Itenerary setDayplan(String dayplan) {
        this.dayplan = dayplan;
        return this;
    }

    public String getPlaceName() {
        return placeName;
    }

    public Itenerary setPlaceName(String placeName) {
        this.placeName = placeName;
        return this;
    }

    public String getPerPersonCost() {
        return perPersonCost;
    }

    public Itenerary setPerPersonCost(String perPersonCost) {
        this.perPersonCost = perPersonCost;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Itenerary setTime(String time) {
        this.time = time;
        return this;
    }

    public String getTransport() {
        return transport;
    }

    public Itenerary setTransport(String transport) {
        this.transport = transport;
        return this;
    }
}
