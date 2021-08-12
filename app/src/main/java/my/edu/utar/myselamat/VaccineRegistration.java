package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VaccineRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_registration);

        Button next = (Button)findViewById(R.id.button);
        next.setOnClickListener(v -> startActivity(new Intent(VaccineRegistration.this, VaccinationDetails.class)));
    }
}