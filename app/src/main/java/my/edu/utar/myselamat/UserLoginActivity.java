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

        if(email.isEmpty()){
            edt_email.setError("Email is required!");
            edt_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_email.setError("Please provide valid email!");
            edt_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edt_password.setError("Password is required!");
            edt_password.requestFocus();
            return;
        }

        if(password.length() < 6 || password.length() > 8){
            edt_password.setError("Password length should be 6 characters!");
            edt_password.requestFocus();
            return;
        }

        progressBar_login.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Intent intent = new Intent(UserLoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(UserLoginActivity.this, "Failed to login! Please key in your details again!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }



}

