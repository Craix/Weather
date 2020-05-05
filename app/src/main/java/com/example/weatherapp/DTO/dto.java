package com.example.weatherapp.DTO;


public class dto {

    private int id;
    private Main main;
    private String name;
    private Coord coord;
    private Wind wind;
    private Weather[] weather;

    public int getId() {
        return id;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public Wind getWind() {
        return wind;
    }

    public Weather[] getWeather() {
        return weather;
    }
}
