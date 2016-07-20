package com.hexazexa.chatapp;

import com.firebase.client.Firebase;

/**
 * Created by Milan on 7/11/2016.
 */
public class CrowdWeather extends  android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
