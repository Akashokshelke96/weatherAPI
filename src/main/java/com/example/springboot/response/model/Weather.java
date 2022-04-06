package com.example.springboot.response.model;
//this file is created to learn how to send JSONs.
public class Weather { //POJO i.e plain old Java Object
    private String cityName;
    private String regionName;

    public Weather() { //default constructor

    }// JACKSON WILL TAKE CARE OF THE response being converted in to JSON for the received response as per the criterion

    public Weather(String cityName, String regionName) { //constructor with all the arguments
        this.cityName = cityName;
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
