package my.edu.utar.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
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

public class VaccinationStatus extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_status);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        Button home;

        final TextView getName = (TextView) findViewById(R.id.getname1);
        final TextView getIC = (TextView) findViewById(R.id.getic1);
        final TextView getStatus = (TextView) findViewById(R.id.getstatus1);
        final TextView getLocation = (TextView) findViewById(R.id.getlocation1);
        final TextView getType = (TextView) findViewById(R.id.gettype1);
        final TextView getDate = (TextView) findViewById(R.id.getdate1);


        home = findViewById(R.id.toHome);

        home.setOnClickListener(v -> {
           Intent intent = new Intent(VaccinationStatus.this, HomeActivity.class);
           startActivity(intent);
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                // create user object and call it as user profile
                UserActivity userProfile = snapshot.getValue(UserActivity.class);

                if(userProfile != null){
                    String name = userProfile.getUsername();
                    String ic = userProfile.getIc();
                    String status = userProfile.getVaccinationStatus();
                    String location = userProfile.getLocation();
                    String type = userProfile.getType();
                    String date = userProfile.getDate();

                    getName.setText(name);
                    getIC.setText(ic);
                    getStatus.setText(status);
                    getLocation.setText(location);
                    getType.setText(type);
                    getDate.setText(date);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(VaccinationStatus.this,"Something wrong happened",Toast.LENGTH_LONG).show();
            }
        });
    }
}