package com.android.meetup.network;

import com.android.meetup.model.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherRestApi {

    @GET("data/2.5/weather")
    public Observable<WeatherData> loadWeatherData(@Query("lat") double latitude, @Query("lon")
    double lon, @Query("appId") String appId);

}
