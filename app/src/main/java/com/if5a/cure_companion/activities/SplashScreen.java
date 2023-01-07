package com.if5a.cure_companion.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.if5a.cure_companion.R;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    ImageView splashImage;
    TextView textTagline, textPresent, textTeam, textCopy ;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );
        getSupportActionBar().hide();

        lottieAnimationView = findViewById(R.id.lottie);
        splashImage = findViewById(R.id.tv_background);
        textTagline = findViewById(R.id.tv_tagline);
        textPresent = findViewById(R.id.tv_present);
        textTeam = findViewById(R.id.tv_team);
        textCopy = findViewById(R.id.tv_copyright);

        lottieAnimationView.animate().translationX(2000).setDuration(1000).setStartDelay(4000);
        splashImage.animate().translationX(-2400).setDuration(1000).setStartDelay(4000);
        textTagline.animate().translationX(2000).setDuration(1000).setStartDelay(4000);
        textPresent.animate().translationX(2000).setDuration(1000).setStartDelay(4000);
        textTeam.animate().translationX(2000).setDuration(1000).setStartDelay(4000);
        textCopy.animate().translationX(2000).setDuration(1000).setStartDelay(4000);

        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);

        new Handler( Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, PilihLogin.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}