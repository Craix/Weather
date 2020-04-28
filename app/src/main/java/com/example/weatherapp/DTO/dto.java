package com.example.weatherapp.DTO;


public class dto {

    private int id;

    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    private Coord coord;

    public Coord getCoord() {
        return coord;
    }

    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    private Main main;

    public Main getMain() {
        return main;
    }
}
