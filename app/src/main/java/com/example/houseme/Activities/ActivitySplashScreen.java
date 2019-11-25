package com.example.houseme.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;

import com.example.houseme.R;
import com.example.houseme.databinding.ActivitySplashScreenBinding;

public class ActivitySplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        binding.splashLogo.animate().alpha(1f).setStartDelay(100).setDuration(1200);

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {

            public void run() {
                Intent intent = new Intent(ActivitySplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
