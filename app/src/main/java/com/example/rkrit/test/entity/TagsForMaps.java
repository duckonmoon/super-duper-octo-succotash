package com.example.rkrit.test.entity;

/**
 * Created by rkrit on 06.10.17.
 */

public class TagsForMaps {
    private long id;
    private double latitude;
    private double longitude;


    public TagsForMaps() {
    }

    public TagsForMaps(long id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude;
    }
}
