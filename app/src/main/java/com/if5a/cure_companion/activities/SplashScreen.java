package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.if5a.cure_companion.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );

        new Handler( Looper.getMainLooper()).postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, PilihLogin.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}