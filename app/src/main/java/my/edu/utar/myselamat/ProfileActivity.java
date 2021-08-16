package my.edu.utar.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = (Button)findViewById(R.id.btn_logout);

        // Call firebase off and then get our instance and get current user.
        user = FirebaseAuth.getInstance().getCurrentUser();
        // We need firebase database and then we need to get instance and then get reference and we are referencing users collection.
        reference = FirebaseDatabase.getInstance().getReference("Users");
        // Get the unique ID of the logged in user
        userID = user.getUid();

        final TextView nameTextView = (TextView) findViewById(R.id.tv_name);
        final TextView idTextView = (TextView) findViewById(R.id.tv_id);
        final TextView icTextView = (TextView) findViewById(R.id.tv_ic);
        final TextView addressLine1TextView = (TextView) findViewById(R.id.tv_addressline1);
        final TextView addressLine2TextView = (TextView) findViewById(R.id.tv_addressline2);
        final TextView stateTextView = (TextView) findViewById(R.id.tv_state);
        final TextView postalCodeTextView = (TextView) findViewById(R.id.tv_postalCode);
        final TextView healthStatusTextView = (TextView) findViewById(R.id.tv_healthStatus);

        // Get realtime database for that user
        // So, we can get the reference and specify a child which is userID, then add listener for single value event and implement the inner class value event listener
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                // create user object and call it as user profile
                UserActivity userProfile = snapshot.getValue(UserActivity.class);

                // Check the user profile is existed
                if(userProfile != null){
                    String username = userProfile.getUsername();
                    String id = userProfile.getPhoneNo();
                    String ic = userProfile.getIc();
                    String addressLine1 = userProfile.getAddressLine1();
                    String addressLine2 = userProfile.getAddressLine2();
                    String state = userProfile.getState();
                    String postalCode = userProfile.getPostalCode();
                    String healthStatus = userProfile.getHealthStatus();

                    // Once we get the value, we need to set it to the layout.
                    nameTextView.setText(username);
                    idTextView.setText(id);
                    icTextView.setText(ic);
                    addressLine1TextView.setText(addressLine1);
                    addressLine2TextView.setText(addressLine2);
                    stateTextView.setText(state);
                    postalCodeTextView.setText(postalCode);
                    healthStatusTextView.setText(healthStatus);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Something wrong happened",Toast.LENGTH_LONG).show();
            }
        });

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, UserLoginActivity.class));
        });

    }

}