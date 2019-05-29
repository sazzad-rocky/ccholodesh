package model;

/**
 * Created by Olivine on 5/6/2017.
 */

public class Trip {
    private String location;
    private String destination;
    private int numberOfTourists;
    private int numberOfdays;
    private String fromDate;
    private String toDate;

    public String getLocation() {
        return location;
    }

    public Trip setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public Trip setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public int getNumberOfTourists() {
        return numberOfTourists;
    }

    public Trip setNumberOfTourists(int numberOfTourists) {
        this.numberOfTourists = numberOfTourists;
        return this;
    }

    public int getNumberOfdays() {
        return numberOfdays;
    }

    public Trip setNumberOfdays(int numberOfdays) {
        this.numberOfdays = numberOfdays;
        return this;
    }

    public String getFromDate() {
        return fromDate;
    }

    public Trip setFromDate(String fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public String getToDate() {
        return toDate;
    }

    public Trip setToDate(String toDate) {
        this.toDate = toDate;
        return this;
    }
}
