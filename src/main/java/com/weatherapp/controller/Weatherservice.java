package com.weatherapp.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import elemental.json.JsonException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Weatherservice {
    private OkHttpClient client;
    private Response response;
    private String cityName;
    private String unit;
    private String APIkey = "ba61e79a0119a86f77cc471643451b24";
    //Getting Data from OpenWeather API
    public JSONPObject getWeather() {
        client = new OkHttpClient(); //using OKHTTP dependency . You have to add this mannually form OKHTTP website
        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?q="+getCityName()+"&units="+getUnit()+"&appid="+APIkey)
                .build();
        try {
            response = client.newCall(request).execute();
            return new JSONPObject(response.body().string());
        }catch (IOException | JsonException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Getting required data from Weather JSON API
    //JSON Objects and JSON Arrays
    public JSONArray returnWeatherArray() throws JsonException {
        JSONArray weatherJsonArray = getWeather().getJSONArray("weather");
        return weatherJsonArray;
    }
    public JSONObject returnMainObject() throws JsonException {
        JSONObject mainObject = getWeather().getJSONObject("main");
        return mainObject;
    }
    public JSONObject returnWindObject() throws JSONException {
        JSONObject wind = getWeather().getJSONObject("wind");
        return wind;
    }

    public JSONObject returnSysObject() throws JSONException{
        JSONObject sys = getWeather().getJSONObject("sys");
        return sys;
    } // to  pull the values of Sys from JSON


    // Getters and Setters for CityName and Unit

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }



}
