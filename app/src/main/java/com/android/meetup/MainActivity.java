package com.android.meetup;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.meetup.manager.LocationManager;
import com.android.meetup.manager.WeatherManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private WeatherManager weatherManager;


    @Bind(R.id.activity_main_image) SimpleDraweeView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inject views using butter knife
        ButterKnife.bind(this);

        locationManager = new LocationManager();
        weatherManager = new WeatherManager();

        // Load current location
        locationManager.loadCurrentLocation().flatMap(latLng ->
                // load weather based on the fetched location
                weatherManager.loadWeatherOfLocation(latLng)
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherData -> {

                    DraweeController draweeController;
                    // If less than 0, show cold GIF
                    if (weatherData.temperature.getTempInCelsius() <= 0) {
                        draweeController = Fresco.newDraweeControllerBuilder()
                                .setUri(Uri.parse("http://www.myangelcardreadings" +
                                        ".com/images/snowdance.gif")).setAutoPlayAnimations(true)
                                .build();
                    } else {
                        // If more than 0, sunny GIF
                        draweeController = Fresco.newDraweeControllerBuilder()
                                .setUri(Uri.parse("http://www.animatedimages" +
                                        ".org/data/media/278/animated-sun-image-0068.gif"))
                                .setAutoPlayAnimations(true).build();
                    }
                    imageView.setController(draweeController);

                }, throwable -> {

                    throwable.printStackTrace();
                });

    }
}
