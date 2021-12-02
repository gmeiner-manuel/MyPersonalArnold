package com.example.personalarnold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_SCREEN_TIMEOUT = 3000;
    EditText name;
    EditText email;
    EditText password;
    Button buttonSignup, buttonNext;
    SharedPreferences sp;
    String nameStr, emailStr, passwordStr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.txt_Username);
        email = findViewById(R.id.txt_Email);
        password = findViewById(R.id.txt_Password);
        buttonSignup = findViewById(R.id.btn_signUp);
        buttonNext = findViewById(R.id.btn_next);

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameStr = name.getText().toString();
                emailStr = email.getText().toString();
                passwordStr = password.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", nameStr);
                editor.putString("email", emailStr);
                editor.putString("password", passwordStr);
                editor.commit();
                Toast.makeText(MainActivity.this, "Information Saved.", Toast.LENGTH_LONG).show();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataDisplayPage.class);
                startActivity(intent);
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, Loadingscreen.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_SCREEN_TIMEOUT);
    }
}