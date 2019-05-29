package model;

public class LatLong {

    private String latitude,longitude,districtname;

    public LatLong(String latitude, String longitude, String districtname) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.districtname = districtname;
    }

    public LatLong() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }
}
