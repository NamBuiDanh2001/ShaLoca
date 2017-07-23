package com.example.hp_bdn.shaloca.model;

/**
 * Created by HP_BDN on 22-Jul-17.
 */

public class Place {
    private  String name_place ;
    private double latitude ;
    private double longitude ;

    public Place() {
    }

    public Place(String name_place) {
        this.name_place = name_place;
    }

    public Place(String name_place, double latitude, double longitude) {
        this.name_place = name_place;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName_place() {
        return name_place;
    }

    public void setName_place(String name_place) {
        this.name_place = name_place;
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
}
