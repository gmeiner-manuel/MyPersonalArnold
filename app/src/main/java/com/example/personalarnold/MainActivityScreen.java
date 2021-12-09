package com.example.personalarnold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityScreen extends AppCompatActivity {

    private Button btn_signIn;
    private TextView btn_signUp;
    private TextView txt_emailAddress;
    private TextView txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        btn_signUp = findViewById(R.id.btn_SignUp);
        txt_emailAddress = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);

        checkEmail();
    }

    public void onSignUp(View view) {
        if (btn_signUp.isPressed()) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    Intent signUpIntent = new Intent(MainActivityScreen.this, SignUpScreen.class);
                    startActivity(signUpIntent);
                    finish();
                }
            });
        }
    }

    public void checkEmail() {
        String demoEmail;
        demoEmail = "123.123@gmail.com";
        if (txt_emailAddress.getText() == demoEmail) {
            txt_emailAddress.setTextColor(Integer.parseInt("#006400"));
        }
    }
}