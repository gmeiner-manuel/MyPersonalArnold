package com.example.personalarnold;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingScreen extends AppCompatActivity {

    //Time to be passed until opening the new screen
    private final int SPLASH_SCREEN_TIMEOUT = 3000;

    /**
     * Initializes the View and sets it
     * @param savedInstanceState is for loading Data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Runs a new Thread which switches to another view
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(LoadingScreen.this, LoginScreen.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_SCREEN_TIMEOUT);
    }
}