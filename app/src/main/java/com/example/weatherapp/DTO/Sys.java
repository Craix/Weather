package com.example.weatherapp.DTO;

public class Sys {
    private int type;
    private int id;
    private String message;
    private String country;
    private Long sunrise;
    private Long sunset;

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }
}
