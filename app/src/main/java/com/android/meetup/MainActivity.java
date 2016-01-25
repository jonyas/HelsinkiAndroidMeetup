package com.android.meetup;

import com.google.gson.GsonBuilder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.meetup.model.WeatherData;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String jsonData = "{\n" +
            "  \"coord\": {\n" +
            "    \"lon\": 24.97,\n" +
            "    \"lat\": 60.17\n" +
            "  },\n" +
            "  \"weather\": [\n" +
            "    {\n" +
            "      \"id\": 801,\n" +
            "      \"main\": \"Clouds\",\n" +
            "      \"description\": \"few clouds\",\n" +
            "      \"icon\": \"02d\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"base\": \"cmc stations\",\n" +
            "  \"main\": {\n" +
            "    \"temp\": 251.79,\n" +
            "    \"pressure\": 1029,\n" +
            "    \"humidity\": 76,\n" +
            "    \"temp_min\": 249.15,\n" +
            "    \"temp_max\": 253.15\n" +
            "  },\n" +
            "  \"wind\": {\n" +
            "    \"speed\": 1,\n" +
            "    \"deg\": 330\n" +
            "  },\n" +
            "  \"clouds\": {\n" +
            "    \"all\": 20\n" +
            "  },\n" +
            "  \"dt\": 1453458618,\n" +
            "  \"sys\": {\n" +
            "    \"type\": 1,\n" +
            "    \"id\": 5018,\n" +
            "    \"message\": 0.0105,\n" +
            "    \"country\": \"FI\",\n" +
            "    \"sunrise\": 1453445823,\n" +
            "    \"sunset\": 1453471616\n" +
            "  },\n" +
            "  \"id\": 653708,\n" +
            "  \"name\": \"Katajanokka\",\n" +
            "  \"cod\": 200\n" +
            "}\n";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Text View
        textView = (TextView) findViewById(R.id.activity_main_text);

        // Load JSON data in a separate thread
        Observable.create(new Observable.OnSubscribe<WeatherData>() {

            @Override
            public void call(Subscriber<? super WeatherData> subscriber) {

                subscriber.onNext(new GsonBuilder().create().fromJson(jsonData, WeatherData.class));
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe
                (weatherData -> {

                    // Show Result
                    textView.setText(String.format("The weather in %s is \"%s\"", weatherData.name,
                            weatherData
                                    .weatherInformationList.get(0).description));
                }, throwable -> {

                    Log.e("MEETUP", "Error", throwable);
                });

    }
}
