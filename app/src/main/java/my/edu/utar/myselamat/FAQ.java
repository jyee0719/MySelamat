package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FAQ extends AppCompatActivity {

    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        home = findViewById(R.id.toHome2);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(FAQ.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}