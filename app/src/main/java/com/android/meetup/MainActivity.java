package com.android.meetup;

import com.google.gson.GsonBuilder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.meetup.model.WeatherData;

public class MainActivity extends AppCompatActivity {

    private final String jsonData = "{\n" +
            "  \"weather\": [\n" +
            "    {\n" +
            "      \"id\": 801,\n" +
            "      \"main\": \"Clouds\",\n" +
            "      \"description\": \"few clouds\",\n" +
            "      \"icon\": \"02d\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"main\": {\n" +
            "    \"temp\": 251.79,\n" +
            "    \"pressure\": 1029,\n" +
            "    \"humidity\": 76,\n" +
            "    \"temp_min\": 249.15,\n" +
            "    \"temp_max\": 253.15\n" +
            "  },\n" +
            "  \"name\": \"Katajanokka\"\n" +
            "}\n";

    private WeatherData weatherData;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Text View
        textView = (TextView) findViewById(R.id.activity_main_text);

        // Parse
        weatherData = new GsonBuilder().create().fromJson(jsonData, WeatherData.class);

        // Show Result
        textView.setText(String.format("The weather in %s is \"%s\"", weatherData.name, weatherData
                .weatherInformationList.get(0).description));
    }
}
