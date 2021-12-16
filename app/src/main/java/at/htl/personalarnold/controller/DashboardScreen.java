package at.htl.personalarnold.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.personalarnold.R;

import at.htl.personalarnold.model.Ser;

public class DashboardScreen extends AppCompatActivity {
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        name = findViewById(R.id.txt_nameDisplay);
        name.setText(Ser.readObject(getApplicationContext()).getUsername());    //ändern das username glovale variable ist
    }
}