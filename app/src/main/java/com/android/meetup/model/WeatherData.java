package com.android.meetup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

        public double getTempInCelsius() {

            return temp - 273.15;
        }

        public double getTempMinInCelsius() {

            return temp_min - 273.15;
        }

        public double getTempMaxInCelsius() {

            return temp_max - 273.15;
        }
    }

    public class Weather {

        public String main;
        public String description;
    }

}
