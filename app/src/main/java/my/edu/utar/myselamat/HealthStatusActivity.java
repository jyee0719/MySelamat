package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HealthStatusActivity extends AppCompatActivity {

    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_status);

        btn_start = findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HealthStatusQuestion();
            }
        });
    }

    // Will proceed to the page of health status question
    public void HealthStatusQuestion(){
        Intent intent = new Intent(HealthStatusActivity.this, HealthStatusQuestionActivity.class);
        startActivity(intent);
    }
}