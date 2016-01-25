package com.android.meetup;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MeetupApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        Fresco.initialize(this);
    }

}
