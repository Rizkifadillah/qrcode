package com.arif.otp.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.arif.otp.R;

/**
 * Created by Angga Kristiono on 27/04/2019.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Login = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(Login);
                finish();

            }
        }, 4000);
    }
}
