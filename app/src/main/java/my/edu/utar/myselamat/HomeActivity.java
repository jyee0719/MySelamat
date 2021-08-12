package my.edu.utar.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button travelButton;
        travelButton = findViewById(R.id.travel_button);
        travelButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TravelActivity.class);
            startActivity(intent);
        });
    }
}
