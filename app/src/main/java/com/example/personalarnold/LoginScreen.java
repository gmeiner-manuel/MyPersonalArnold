package com.example.personalarnold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    private Button btn_signIn;
    private TextView btn_signUp;
    private TextView txt_email;
    private TextView txt_password;

    private final LoginCheck check = new LoginCheck();

    /**
     * Method onCreate initializes the content on the view when the View is being displayed
     * for the first time
     * @param savedInstanceState is for loading Data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        btn_signUp = findViewById(R.id.btn_SignUp);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
    }

    /**
     * Method onSignUp is called when the Button btn_signUp is pressed.
     * It starts a new Thread which then opens a new View.
     * @param view View of the app
     */
    public void onSignUp(View view) {
        if (btn_signUp.isPressed()) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    Intent signUpIntent = new Intent(LoginScreen.this, SignUpScreen.class);
                    startActivity(signUpIntent);
                    finish();
                }
            });
        }
    }

    /**
     * Signs in the User if the provided information are correct
     * @param view View of the App
     */
    public void onSignIn(View view){
        if(check.checkLoginData(txt_email.getText().toString(), txt_password.getText().toString(), getApplicationContext())){
            Intent intent = new Intent(LoginScreen.this, Dashboard.class);
            startActivity(intent);
            Toast.makeText(LoginScreen.this, "Successful login", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(LoginScreen.this, "Wrong Email or Password", Toast.LENGTH_LONG).show();
        }
    }
}