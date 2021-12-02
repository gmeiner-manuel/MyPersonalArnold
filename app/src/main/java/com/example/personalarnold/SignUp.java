package com.example.personalarnold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    SeekBar age;
    Button buttonSignup, buttonNext;
    SharedPreferences sp;
    String nameStr, emailStr, passwordStr, ageStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.txt_Username);
        email = findViewById(R.id.txt_Email);
        password = findViewById(R.id.txt_Password);
        age = findViewById(R.id.sb_age);
        buttonSignup = findViewById(R.id.btn_signUp);
        buttonNext = findViewById(R.id.btn_next);

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        buttonSignup.setOnClickListener(v -> {
            nameStr = name.getText().toString();
            emailStr = email.getText().toString();
            passwordStr = password.getText().toString();
            ageStr = age.getContext().toString();

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name", nameStr);
            editor.putString("email", emailStr);
            editor.putString("password", passwordStr);
            editor.putString("age", ageStr);
            editor.apply();
            Toast.makeText(SignUp.this, "Information Saved.", Toast.LENGTH_LONG).show();
        });

        buttonNext.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, DataDisplayPage.class);
            startActivity(intent);
        });
    }
}