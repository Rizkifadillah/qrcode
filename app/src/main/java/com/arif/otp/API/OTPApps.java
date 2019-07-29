package com.arif.otp.API;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

/**
 * Created by Angga Kristiono on 27/04/2019.
 */

public class OTPApps extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
