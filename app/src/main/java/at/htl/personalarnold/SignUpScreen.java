package at.htl.personalarnold;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalarnold.R;

import java.util.Date;

public class SignUpScreen extends AppCompatActivity {

    EditText name, email, password, repassword;
    TextView age;
    Button buttonSignup, buttonNext, buttonSelectBirthday;
    SharedPreferences sp;
    String nameStr, emailStr, passwordStr, repasswordStr, ageStr, txtAge, txtDate;

    private final DataCheck check = new DataCheck();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        name = findViewById(R.id.txt_Username);
        email = findViewById(R.id.txt_Email);
        password = findViewById(R.id.txt_Password);
        repassword = findViewById(R.id.txt_Repassword);
        age = findViewById(R.id.txt_Age);
        buttonSignup = findViewById(R.id.btn_signUp);
        //buttonNext = findViewById(R.id.btn_next);
        //buttonSelectBirthday = findViewById(R.id.btn_selectBirthday);

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);


        /*When Button is pressed -> Opens a new Activity, in this case the DataDisplayPage
        buttonNext.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpScreen.this, DataDisplayPage.class);
            startActivity(intent);
        });
         */

    }

    /**
     * Sign Up button to register the user
     * @param view View of the app
     */
    public void onSignUp(View view){
        nameStr = name.getText().toString();
        emailStr = email.getText().toString();
        passwordStr = password.getText().toString();
        repasswordStr = repassword.getText().toString();
        ageStr = txtAge;

        checkField();

        /*
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
            System.out.println("User is over 16! Sign him Up");
        }
        else if (checkAge() == false) {
            System.out.println("User is under 16! Do NOT Sign him Up");
        }
         */
    }

    /**
     * Redirects to the Login Screen
     * @param view View of the app
     */
    public void onSignIn(View view){
        Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
        startActivity(intent);
    }

    /**
     * When the Button buttonSelectBirthday is pressed -> Creates a Calendar Object and
     * saves the Date as separate Integers - one for day, one for month and one for year.
     * It then shows the content in the DatePickerDialog
     * @param view View of the app
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onSelectBirthday(View view){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dateDialog = new DatePickerDialog(view.getContext(), dataPickerListener,  year, month, day);
            dateDialog.getDatePicker().setMaxDate(new Date().getTime());
            dateDialog.show();
    }

    /**
     * Creates a new DatePickerDialog which gets the Values of the Calendar
     */
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
            age.setText("Age " + txtAge);  //displays the age
        }
    };

    /**
     * Checks if the input fields are empty/ checks if password matches conditions
     * Also sets an ErrorIcon if something is false
     */
    public void checkField(){
        //Checks if textfield Name is empty
        if(nameStr.isEmpty()){
            name.setError("This field can not be empty!");
        }
        //Checks if textfield E-Mail is empty
        if(emailStr.isEmpty()){
            email.setError("This field can not be empty!");
        }
        //Checks if the reentered password equals the password
        if(!passwordStr.equals(repasswordStr)){
            repassword.setError("Reentered Password does not match with Password!");
        }
        //checks if the age is null
        if(ageStr == null){
            age.setText("Age :(");
        }

        //checks if the password fullfils every conditiom
        //if true User gets registered, else Error
        if(check.checkSignUpPW(passwordStr)){
            //Checks if reentered password and password match
            if(passwordStr.equals(repasswordStr) && !nameStr.isEmpty() && !emailStr.isEmpty() && ageStr != null){
                Ser.writeObject(new User(emailStr, nameStr, passwordStr, Integer.parseInt(ageStr)), getApplicationContext());
                System.out.println(Ser.readObject(getApplicationContext()).toString());
                Toast.makeText(SignUpScreen.this, "Successful Signup", Toast.LENGTH_LONG).show();
            }
            /*
            //redirects to the login Screen
            Intent intent = new Intent(SignUpScreen.this, Dashboard.class);
            startActivity(intent);
             */
        }
        else{
            System.out.println("Something is missing");
            password.setError("Your passwords needs:\nAt least 8 characters\nNumeric Number\nSymbol (!, ?, $, etc.");
        }
    }

    /**
     * Calculates the Age using the inputs from the Calendar.
     * @param date gets the value of the Calendar and gives it the
     * TimeInMillis. It's a long value since it's a milli second value.
     * @return returns the age
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
     * @return returns true if age is over 16
     */
    public boolean checkAge() {
        int age;
        try {
            age = Integer.parseInt(txtAge);
        }
        catch (Exception e){
            age = 16;
        }
        boolean overSixteen;
        if (age >= 16) {
            overSixteen = true;
        }
        else {
            overSixteen = false;
        }
        return overSixteen;
    }
}