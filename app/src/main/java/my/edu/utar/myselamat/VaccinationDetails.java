package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VaccinationDetails extends AppCompatActivity {

    private EditText interest, disabled, allergic,allergies;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_details);

        interest = (EditText) findViewById(R.id.interest);
        disabled = (EditText) findViewById(R.id.disabled);
        allergic = (EditText) findViewById(R.id.allergic);
        allergies = (EditText) findViewById(R.id.allergies);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(v -> VaccinationDetails());
    }

    private void VaccinationDetails(){
        String interest1  = interest.getText().toString().trim();
        String disabled1 = disabled.getText().toString().trim();
        String allergic1 = allergic.getText().toString().trim();

        if (interest1.isEmpty()) {
            interest.setError("Please fill in Yes or No");
            interest.requestFocus();
            return;
        }

        if (disabled1.isEmpty()) {
            disabled.setError("Please fill in Yes or No");
            disabled.requestFocus();
            return;
        }

        if (allergic1.isEmpty()) {
            allergic.setError("Please fill in Yes or No");
            allergic.requestFocus();
            return;
        }

        else
        {
            startActivity(new Intent(VaccinationDetails.this, ThankYou.class));
        }
    }
}

