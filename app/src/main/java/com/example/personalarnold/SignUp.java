package com.example.personalarnold;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    List<String> ages = new ArrayList<>();
    Button btn_signUp;
    TextView txt_email;
    TextView txt_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_signUp = findViewById(R.id.btn_signUp);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);

        addData();

    }

    public void addData() {
        for (int i = 0; i < 100; i++) {
            ages.add("" + i + "");
        }
    }

    public void onSignUp(View view) {
        if (btn_signUp.isPressed()) {
            checkEmail();
            checkPassword();
            finish();
        }
    }
    public void checkEmail() {
        if (!txt_email.getText().toString().contains("@") && (!txt_email.getText().toString().contains("."))) {
            txt_email.setTextColor(Integer.parseInt("#FF0000"));
        }
    }

    public void checkPassword() {
        if (txt_password.getText().length() < 8) {
            txt_password.setTextColor(Integer.parseInt("#FF0000"));
        }
    }
}