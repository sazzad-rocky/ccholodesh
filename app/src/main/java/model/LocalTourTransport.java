package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalTourTransport {

    @SerializedName("local_tour_transport_type_id")
    @Expose
    private Integer localTourTransportTypeId;
    @SerializedName("local_tour_transport_type_local_tour_id")
    @Expose
    private String localTourTransportTypeLocalTourId;
    @SerializedName("local_tour_transport_type_name_id")
    @Expose
    private String localTourTransportTypeNameId;
    @SerializedName("tt_id")
    @Expose
    private Integer ttId;
    @SerializedName("tt_name")
    @Expose
    private String ttName;

    public Integer getLocalTourTransportTypeId() {
        return localTourTransportTypeId;
    }

    public void setLocalTourTransportTypeId(Integer localTourTransportTypeId) {
        this.localTourTransportTypeId = localTourTransportTypeId;
    }

    public String getLocalTourTransportTypeLocalTourId() {
        return localTourTransportTypeLocalTourId;
    }

    public void setLocalTourTransportTypeLocalTourId(String localTourTransportTypeLocalTourId) {
        this.localTourTransportTypeLocalTourId = localTourTransportTypeLocalTourId;
    }

    public String getLocalTourTransportTypeNameId() {
        return localTourTransportTypeNameId;
    }

    public void setLocalTourTransportTypeNameId(String localTourTransportTypeNameId) {
        this.localTourTransportTypeNameId = localTourTransportTypeNameId;
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

}