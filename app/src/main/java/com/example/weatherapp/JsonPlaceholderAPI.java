package com.example.weatherapp;

import com.example.weatherapp.DTO.dto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceholderAPI {
    @GET("data/2.5/weather")
    Call<dto> getDto(@Query("q") String q, @Query("units") String units, @Query("APPID") String APPID);
}

