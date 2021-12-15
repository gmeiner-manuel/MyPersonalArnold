package at.htl.personalarnold;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalarnold.R;

/**
 * Data is being displayed on this page.
 * Used for checking the data and using it for SignIn purposes.
 */

public class DataDisplayPage extends AppCompatActivity {

    /**
     * Method onCreate initializes the content on the view being used
     * to display the data.
     * @param savedInstanceState is for passing Data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);
        TextView t1, t2, t3, t4;
        //Getting the ID from the XML file and initializing the TextViews
        t1 = findViewById(R.id.txt_displayName);
        t2 = findViewById(R.id.txt_displayEmail);
        t3 = findViewById(R.id.txt_displayPassword);
        t4 = findViewById(R.id.txt_displayAge);
        //Used to save data locally on the device
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        String email = sp.getString("email", "");
        String password = sp.getString("password", "");
        String age = sp.getString("age", "");
        t1.setText(name);
        t2.setText(email);
        t3.setText(password);
        t4.setText(age);

    }

}
