package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ThankYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        Button home = (Button)findViewById(R.id.button7);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThankYou.this, HomeActivity.class));
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.getString("some")!=null){
                Toast.makeText(getApplicationContext(),"data: "+bundle.getString("some"), Toast.LENGTH_LONG).show();
            }
        }
    }
}