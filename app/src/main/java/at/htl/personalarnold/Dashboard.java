package at.htl.personalarnold;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.personalarnold.R;

public class Dashboard extends AppCompatActivity {
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        name = findViewById(R.id.txt_nameDisplay);
        name.setText(Ser.readObject(getApplicationContext()).getUsername());    //ändern das username glovale variable ist
    }
}