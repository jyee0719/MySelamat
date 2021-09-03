package my.edu.utar.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserRegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText edt_username, edt_ic, edt_addressLine1, edt_addressLine2, edt_postalCode, edt_phoneNo, edt_email, edt_password;
    private Spinner dropdown_state;
    private Button btn_submit_register;
    private ProgressBar progressBar;
    private ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    private ArrayList<String> emailList, phoneNoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        // Initialize firebase
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        emailList = new ArrayList<>();
        phoneNoList = new ArrayList<>();

        // Get all the user email and phone number from the firebase
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    UserActivity ua = ds.getValue(UserActivity.class);
                    emailList.add(ua.getEmail());
                    phoneNoList.add(ua.getPhoneNo());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        };

        edt_username = (EditText) findViewById(R.id.et_newUserName);
        edt_ic = (EditText) findViewById(R.id.et_user_ic);
        edt_addressLine1 = (EditText) findViewById(R.id.et_user_addressline1);
        edt_addressLine2 = (EditText) findViewById(R.id.et_user_addressline2);
        dropdown_state = (Spinner) findViewById(R.id.user_state);
        edt_postalCode = (EditText) findViewById(R.id.et_user_postalCode);
        edt_phoneNo = (EditText) findViewById(R.id.et_user_phoneNo);
        edt_email = (EditText) findViewById(R.id.et_user_email);
        edt_password = (EditText) findViewById(R.id.et_user_password);
        btn_submit_register = (Button) findViewById(R.id.btn_reg_submit);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        String[] items = new String[]{"Kuala Lumpur", "Labuan", "Putrajaya", "Johor", "Kedah", "Kelantan", "Malacca", "Negeri Sembilan", "Pahang",
                "Perak", "Perlis", "Pulau Pinang", "Sabah", "Sarawak", "Selangor", "Terengganu"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown_state.setAdapter(adapter);

        btn_submit_register.setOnClickListener(v -> {
            databaseReference.child("Users").addListenerForSingleValueEvent(eventListener);
            String str;
            // Check all the email for preventing duplication record
            str = "";
            for (String s : emailList) str += s + " ";
            Log.i("Check Email", str);
            // Check all the phone number for preventing duplication record
            str = "";
            for (String s : phoneNoList) str += s + " ";
            Log.i("Check Phone Number", str);
            if (!emailList.isEmpty() && !phoneNoList.isEmpty()) {
                UserRegistrationActivity();
            }
        });
    }

    private void UserRegistrationActivity() {
        String username = edt_username.getText().toString().trim();
        String ic = edt_ic.getText().toString().trim();
        String addressLine1 = edt_addressLine1.getText().toString().trim();
        String addressLine2 = edt_addressLine2.getText().toString().trim();
        String postalCode = edt_postalCode.getText().toString().trim();
        String phoneNo = edt_phoneNo.getText().toString().trim();
        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        String state = dropdown_state.getSelectedItem().toString();

        // Implement validation
        // If the name is empty, the error message will pop up.
        if (username.isEmpty()) {
            edt_username.setError("Full name is required!");
            edt_username.requestFocus();
            return;
        }

        // If the ic or passport number is empty, the error message will pop up.
        if (ic.isEmpty()) {
            edt_ic.setError("IC OR Passport number is required!");
            edt_ic.requestFocus();
            return;
        }

        // If the entered ic is not in 12-digits or the entered passport number is not in 8-digits, the error message will pop up.
        if (ic.length() != 8 && ic.length() != 12) {
            edt_ic.setError("IC should be 12-digits and passport number should be 8-digits!");
            edt_ic.requestFocus();
            return;
        }

        // If the address line 1 is empty, the error message will pop up.
        if (addressLine1.isEmpty()) {
            edt_addressLine1.setError("AddressLine1 is required!");
            edt_addressLine1.requestFocus();
            return;
        }

        // If the postal code is empty, the error message will pop up.
        if (postalCode.isEmpty()) {
            edt_postalCode.setError("Postal code is required!");
            edt_postalCode.requestFocus();
            return;
        }

        // If the entered postal code is not in 5-digits, the error message will pop up.
        if (postalCode.length() != 5) {
            edt_postalCode.setError("Postal code should be 5-digits!");
            edt_postalCode.requestFocus();
            return;
        }

        // If the phone number is empty, the error message will pop up.
        if (phoneNo.isEmpty()) {
            edt_phoneNo.setError("Phone number is required!");
            edt_phoneNo.requestFocus();
            return;
        }

        // If the phone number is not in 10-digits or 11-digits, the error message will pop up.
        if (phoneNo.length() < 10 || phoneNo.length() > 11) {
            edt_phoneNo.setError("Phone number should be 10-digits or 11-digits!");
            edt_phoneNo.requestFocus();
            return;
        }

        // If the entered phone number has been duplicated with another phone number, the error message will pop up.
        if (phoneNoList.contains(phoneNo)) {
            edt_phoneNo.setError("Phone Number already exist!");
            edt_phoneNo.requestFocus();
            return;
        }

        // If the email is empty, the error message will pop up.
        if (email.isEmpty()) {
            edt_email.setError("Email is required!");
            edt_email.requestFocus();
            return;
        }

        // If the entered email has been duplicated with another email, the error message will pop up.
        if (emailList.contains(email)) {
            edt_email.setError("Email already exist!");
            edt_email.requestFocus();
            return;
        }

        // Validate the email pattern
        // If the entered email pattern is not correct, the error message will pop up.
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_email.setError("Please provide valid email!");
            edt_email.requestFocus();
            return;
        }

        // If the password is empty, the error message will pop up.
        if (password.isEmpty()) {
            edt_password.setError("Password is required!");
            edt_password.requestFocus();
            return;
        }

        // If the entered password is not in the range of 6-8 characters, the error message will pop up.
        if (password.length() < 6 || password.length() > 8) {
            edt_password.setError("Password length should be in the range of 6-8 characters!");
            edt_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // Register the user in Firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {  //if user is created successfully
                            // Put user name, ic, address line 1, address line 2, postal code, phone number, email, state into realtime firebase.
                            UserActivity user = new UserActivity(username, ic, addressLine1, addressLine2, postalCode, phoneNo, email, state);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(UserRegistrationActivity.this,"Account created.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(UserRegistrationActivity.this, HomeActivity.class));
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(UserRegistrationActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                    }
                });
    }
}

