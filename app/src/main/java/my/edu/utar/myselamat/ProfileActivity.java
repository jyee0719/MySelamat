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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userID;
    private Button logout, update_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = (Button)findViewById(R.id.btn_logout);
        update_profile = (Button)findViewById(R.id.btn_updateProfile);

        // Initialize firebase
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        // Get current userID
        userID = user.getUid();

        final EditText nameEditText = (EditText) findViewById(R.id.edt_name);
        final EditText idEditText = (EditText) findViewById(R.id.edt_id);
        // Disable the idEditText
        // Because it is an unchangeable mySelamat ID.
        idEditText.setEnabled(false);
        final EditText icEditText = (EditText) findViewById(R.id.edt_ic);
        final EditText addressLine1EditText = (EditText) findViewById(R.id.edt_addressLine1);
        final EditText addressLine2EditText = (EditText) findViewById(R.id.edt_addressLine2);
        final EditText stateEditText = (EditText) findViewById(R.id.edt_state);
        final EditText postalCodeEditText = (EditText) findViewById(R.id.edt_postalCode);
        final EditText healthStatusEditText = (EditText) findViewById(R.id.edt_healthStatus);
        // Disable the healthStatusEditText
        // Because it is an updated health status.
        healthStatusEditText.setEnabled(false);

        // Find the userID and retrieve the current user data
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                UserActivity userProfile = snapshot.getValue(UserActivity.class);

                // If the user is login, get the user details from Firebase
                if(userProfile != null){
                    String username = userProfile.getUsername();
                    String id = userProfile.getPhoneNo();
                    String ic = userProfile.getIc();
                    String addressLine1 = userProfile.getAddressLine1();
                    String addressLine2 = userProfile.getAddressLine2();
                    String state = userProfile.getState();
                    String postalCode = userProfile.getPostalCode();
                    String healthStatus = userProfile.getHealthStatus();

                    // Display the user details
                    nameEditText.setText(username);
                    idEditText.setText(id);
                    icEditText.setText(ic);
                    addressLine1EditText.setText(addressLine1);
                    addressLine2EditText.setText(addressLine2);
                    stateEditText.setText(state);
                    postalCodeEditText.setText(postalCode);
                    healthStatusEditText.setText(healthStatus);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Something wrong happened",Toast.LENGTH_LONG).show();
            }
        });

        // Logout
        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, UserLoginActivity.class));
        });

        // Update the user information in user profile
        update_profile.setOnClickListener(v -> {
            String new_username= nameEditText.getText().toString().trim();
            String new_ic = icEditText.getText().toString().trim();
            String new_addressLine1 = addressLine1EditText.getText().toString().trim();
            String new_addressLine2 = addressLine2EditText.getText().toString().trim();
            String new_state = stateEditText.getText().toString().trim();
            String new_postalCode = postalCodeEditText.getText().toString().trim();

            // Implement validation
            // If the name is empty, the error message will be popped up.
            if (new_username.isEmpty()) {
                nameEditText.setError("Full name is required!");
                nameEditText.requestFocus();
                return;
            }

            // If the ic or passport number is empty, the error message will be popped up.
            if (new_ic.isEmpty()) {
                icEditText.setError("IC OR Passport number is required!");
                icEditText.requestFocus();
                return;
            }

            // If the entered ic is not in 12-digits or the entered passport number is not in 8-digits, the error message will be popped up.
            if (new_ic.length() != 8 && new_ic.length() != 12) {
                icEditText.setError("IC should be 12-digits and passport number should be 8-digits!");
                icEditText.requestFocus();
                return;
            }

            // If the address line 1 is empty, the error message will be popped up.
            if (new_addressLine1.isEmpty()) {
                addressLine1EditText.setError("Address Line 1 is required!");
                addressLine1EditText.requestFocus();
                return;
            }

            // If the state is empty, the error message will be popped up.
            if (new_state.isEmpty()) {
                stateEditText.setError("State is required!");
                stateEditText.requestFocus();
                return;
            }

            // If the postal code is empty, the error message will be popped up.
            if (new_postalCode.isEmpty()) {
                postalCodeEditText.setError("Postal code is required!");
                postalCodeEditText.requestFocus();
                return;
            }

            // If the entered postal code is not in 5-digits, the error message will be popped up.
            if (new_postalCode.length() != 5) {
                postalCodeEditText.setError("Postal code should be 5-digits!");
                postalCodeEditText.requestFocus();
                return;
            }

            HashMap hashMap = new HashMap();
            //update the value to Firebase
            hashMap.put("username", new_username);
            hashMap.put("ic", new_ic);
            hashMap.put("addressLine1", new_addressLine1);
            hashMap.put("addressLine2", new_addressLine2);
            hashMap.put("state", new_state);
            hashMap.put("postalCode", new_postalCode);

            // When the data is successfully updated, the toast message will be popped out.
            databaseReference.child(userID).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Toast.makeText(ProfileActivity.this, "Your profile is updated successfully.", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

}