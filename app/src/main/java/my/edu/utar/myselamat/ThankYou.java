package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ThankYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        Button home;
        home = findViewById(R.id.toHome1);

        home.setOnClickListener(v -> {
            Intent intent = new Intent(ThankYou.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}