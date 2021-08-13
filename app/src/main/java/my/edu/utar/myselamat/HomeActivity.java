package my.edu.utar.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button travel_button,sop_button,hotspot_button,health_status_button,register_vaccine_button,check_vaccine_button,locate_health_button;
        ImageView profile, home1;

        travel_button = findViewById(R.id.travel_button);
        sop_button = findViewById(R.id.sop_button);
        hotspot_button = findViewById(R.id.hotspot_button);
        health_status_button = findViewById(R.id.health_status_button);
        register_vaccine_button = findViewById(R.id.register_vaccine_button);
        check_vaccine_button = findViewById(R.id.check_vaccine_button);
        locate_health_button = findViewById(R.id.locate_health_button);
        profile = findViewById(R.id.profile);
        home1 = findViewById(R.id.home1);

        travel_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TravelActivity.class);
            startActivity(intent);
        });

        sop_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UserLoginActivity.class);
            startActivity(intent);
        });

        hotspot_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TravelActivity.class);
            startActivity(intent);
        });

        health_status_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TravelActivity.class);
            startActivity(intent);
        });

        register_vaccine_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, VaccineRegistration.class);
            startActivity(intent);
        });

        check_vaccine_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TravelActivity.class);
            startActivity(intent);
        });

        locate_health_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TravelActivity.class);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        home1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}
