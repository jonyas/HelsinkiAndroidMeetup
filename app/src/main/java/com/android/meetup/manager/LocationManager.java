package com.android.meetup.manager;

import com.google.android.gms.maps.model.LatLng;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class LocationManager {

    public Observable<LatLng> loadCurrentLocation() {

        return Observable.timer(2, TimeUnit.SECONDS).map(aLong -> {
            // Simulating fetching location
            return new LatLng(60.1708, 24.9375);
        });
    }
}
