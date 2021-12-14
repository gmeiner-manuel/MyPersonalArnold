package com.example.personalarnold;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class SignUpScreen extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    Button buttonSignup, buttonNext, buttonSelectBirthday;
    SharedPreferences sp;
    String nameStr, emailStr, passwordStr, ageStr, txtAge, txtDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.txt_Username);
        email = findViewById(R.id.txt_Email);
        password = findViewById(R.id.txt_Password);
        buttonSignup = findViewById(R.id.btn_signUp);
        buttonNext = findViewById(R.id.btn_next);
        buttonSelectBirthday = findViewById(R.id.btn_selectBirthday);

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        buttonSignup.setOnClickListener(v -> {
            nameStr = name.getText().toString();
            emailStr = email.getText().toString();
            passwordStr = password.getText().toString();
            ageStr = txtAge;

            // Start Shared Preferences Code
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name", nameStr);
            editor.putString("email", emailStr);
            editor.putString("password", passwordStr);
            editor.putString("age", ageStr);
            editor.apply();
            Toast.makeText(SignUpScreen.this, "Information Saved.", Toast.LENGTH_LONG).show();
            // End Preferences Code


            // TODO: Create Object and store values
            // Object should look like:
            // User user = new User(nameStr, emailStr, passwordStr, ageStr);
            System.out.println(nameStr);
            System.out.println(emailStr);
            System.out.println(passwordStr);
            System.out.println(ageStr);
            // TODO: Save Object in File
            // Pseudo-Code file.setText(file.currentText + user.toString);

            if (checkAge() == true) {
                System.out.println("User is over 14! Sign him Up");
            }
            else if (checkAge() == false) {
                System.out.println("User is under 14! Do NOT Sign him Up");
            }
        });

        //When Button is pressed -> Opens a new Activity, in this case the DataDisplayPage
        buttonNext.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpScreen.this, DataDisplayPage.class);
            startActivity(intent);
        });

        /**
         * When the Button buttonSelectBirthday is pressed -> Creates a Calendar Object and
         * saves the Date as separate Integers - one for day, one for month and one for year.
         * It then shows the content in the DatePickerDialog
         */
        buttonSelectBirthday.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), dataPickerListener,  year, month, day);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }
        });
    }

    /**
     * Creates a new DatePickerDialog which gets the Values of the Calendar
     * **/
    private DatePickerDialog.OnDateSetListener dataPickerListener = new DatePickerDialog.OnDateSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String format = new SimpleDateFormat("dd MMM YYYY").format(c.getTime());
            txtDate = format;
            txtAge = Integer.toString(calculateAge(c.getTimeInMillis()));
        }
    };

    /**
     * Calculates the Age using the inputs from the Calendar.
     * @param date gets the value of the Calendar and gives it the
     * TimeInMillis. It's a long value since it's a milli second value.
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int calculateAge(long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    /**
     * Checks the Age. If the Age is below 16, the user is not able to sign up.
     * If the Age is above 16, the user will be able to sign up. The retun value
     * is a boolean which is true when the user is old enough and false if not.
     * @return
     */
    public boolean checkAge() {
        int age = Integer.parseInt(txtAge);
        boolean overSixteen;
        if (age >= 14) {
            overSixteen = true;
        }
        else {
            overSixteen = false;
        }
        return overSixteen;
    }
}