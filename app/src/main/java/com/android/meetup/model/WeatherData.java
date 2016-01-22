package com.android.meetup.model;

import com.google.gson.annotations.SerializedName;

/**
 * Weather POJO
 *
 * Created by jduran on 22/01/16.
 */
public class WeatherData {

    @SerializedName("main")
    public Main weatherInfo;
    public Weather weather;

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
