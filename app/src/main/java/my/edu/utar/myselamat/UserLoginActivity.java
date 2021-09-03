package my.edu.utar.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class UserLoginActivity extends AppCompatActivity {

    private Button btn_register, btn_signIn, btn_forgot_pwd;
    private EditText edt_email, edt_password;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        btn_register = (Button)findViewById(R.id.btn_signUp);
        btn_signIn = (Button)findViewById(R.id.btn_signIn);
        edt_email = (EditText)findViewById(R.id.et_login_email);
        edt_password = (EditText)findViewById(R.id.et_login_password);
        progressBar_login = (ProgressBar)findViewById(R.id.progressBar_login);
        btn_forgot_pwd = (Button)findViewById(R.id.btn_forgot_password);

        // Initialize firebase
        mAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(v -> {
            startActivity(new Intent(UserLoginActivity.this,UserRegistrationActivity.class));
        });

        btn_signIn.setOnClickListener(v -> {
            UserLoginActivity();
        });

        btn_forgot_pwd.setOnClickListener(v ->{
            startActivity(new Intent(UserLoginActivity.this,ForgotPasswordActivity.class));
        });
    }

    private void UserLoginActivity(){
        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        // Implement validation
        // If the email is empty, the error message will pop up.
        if(email.isEmpty()){
            edt_email.setError("Email is required!");
            edt_email.requestFocus();
            return;
        }

        // Validate the email pattern
        // If the entered email pattern is not correct, the error message will pop up.
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_email.setError("Please provide valid email!");
            edt_email.requestFocus();
            return;
        }

        // If the password is empty, the error message will pop up.
        if(password.isEmpty()){
            edt_password.setError("Password is required!");
            edt_password.requestFocus();
            return;
        }

        // If the entered password is not in the range of 6-8 characters, the error message will pop up.
        if(password.length() < 6 || password.length() > 8){
            edt_password.setError("Password length should be in the range of 6-8 characters!");
            edt_password.requestFocus();
            return;
        }

        progressBar_login.setVisibility(View.GONE);

        // Authenticate the user
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                // Check login successful or not
                if(task.isSuccessful()){   // If user login is successful
                    Toast.makeText(UserLoginActivity.this,"Login successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserLoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(UserLoginActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}

