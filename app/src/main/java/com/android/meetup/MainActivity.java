package com.android.meetup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.meetup.manager.LocationManager;
import com.android.meetup.manager.WeatherManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private WeatherManager weatherManager;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = new LocationManager();
        weatherManager = new WeatherManager();

        // Text View
        textView = (TextView) findViewById(R.id.activity_main_text);

        // Load current location
        locationManager.loadCurrentLocation().flatMap(latLng ->
                // load weather based on the fetched location
                weatherManager.loadWeatherOfLocation(latLng)
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherData -> {
                    // Show Result
                    textView.setText(String.format("The weather in %s is \"%s\" and temperature " +
                            "is %.2fºC", weatherData.name, weatherData.weatherInformationList.get(0)
                            .description, weatherData.temperature.getTempInCelsius()));
                }, throwable -> {

                    Log.e("MEETUP", "Error", throwable);
                });

    }
}
