package com.android.meetup.manager;

import com.google.android.gms.maps.model.LatLng;

import com.android.meetup.model.WeatherData;
import com.android.meetup.network.WeatherRestApi;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;

public class WeatherManager {

    private final static String BASE_URL = "http://api.openweathermap.org/";
    private final static String APP_ID = "44db6a862fba0b067b1930da0d769e98";

    private final WeatherRestApi weatherRestApi;

    public WeatherManager() {

        weatherRestApi = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build().create(WeatherRestApi.class);
    }

    public Observable<WeatherData> loadWeatherOfLocation(LatLng locationToFetchWeather) {

        return weatherRestApi.loadWeatherData(locationToFetchWeather.latitude,
                locationToFetchWeather.longitude, APP_ID);
    }

}
