package my.edu.utar.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class VaccineRegistration extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_registration);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView showName = (TextView) findViewById(R.id.showname1);
        final TextView showIC = (TextView) findViewById(R.id.showic1);
        final TextView showAdd1 = (TextView) findViewById(R.id.add1);
        final TextView showAdd2 = (TextView) findViewById(R.id.add2);
        final TextView showState = (TextView) findViewById(R.id.showstate1);
        final TextView showPostal = (TextView) findViewById(R.id.showpostal1);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                // create user object and call it as user profile
                UserActivity userProfile = snapshot.getValue(UserActivity.class);

                if(userProfile != null){
                    String username = userProfile.getUsername();
                    String ic = userProfile.getIc();
                    String add1 = userProfile.getAddressLine1();
                    String add2 = userProfile.getAddressLine2();
                    String state = userProfile.getState();
                    String postal = userProfile.getPostalCode();

                    showName.setText(username);
                    showIC.setText(ic);
                    showAdd1.setText(add1);
                    showAdd2.setText(add2);
                    showState.setText(state);
                    showPostal.setText(postal);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(VaccineRegistration.this,"Something wrong happened",Toast.LENGTH_LONG).show();
            }
        });

        Button next = (Button)findViewById(R.id.button);
        next.setOnClickListener(v -> startActivity(new Intent(VaccineRegistration.this, VaccinationDetails.class)));
    }
}