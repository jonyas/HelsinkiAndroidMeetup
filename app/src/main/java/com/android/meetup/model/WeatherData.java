package com.android.meetup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Weather POJO
 *
 * Created by jduran on 22/01/16.
 */
public class WeatherData {

    @SerializedName("main")
    public Main temperature;
    @SerializedName("weather")
    public List<Weather> weatherInformationList;

    public String name;

    public class Main {

        public double temp;
        public double temp_min;
        public double temp_max;
    }

    public class Weather {

        public String main;
        public String description;
    }

}
