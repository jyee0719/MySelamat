package my.edu.utar.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailForResetPwd;
    private Button btn_resetPwd;
    private ProgressBar progressBarForResetPwd;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailForResetPwd = (EditText)findViewById(R.id.edt_emailForReset);
        btn_resetPwd = (Button)findViewById(R.id.btn_forgot_password);
        progressBarForResetPwd = (ProgressBar)findViewById(R.id.progressBar_ResetPassword);

        auth = FirebaseAuth.getInstance();

        btn_resetPwd.setOnClickListener(v -> {
            resetPassword();
        });
    }

    private void resetPassword(){
        String email = emailForResetPwd.getText().toString().trim();

        // Implement validation
        // If the email is empty, the error message will be popped up.
        if(email.isEmpty()){
            emailForResetPwd.setError("Full name is required!");
            emailForResetPwd.requestFocus();
            return;
        }

        // Validate the email pattern
        // If the entered email pattern is not correct, the error message will pop up.
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailForResetPwd.setError("Please provide valid email!");
            emailForResetPwd.requestFocus();
            return;
        }

        // If the process of resetting the password is successful, the reset link will be sent to the email.
        // When a user clicks that link, a popup window will appear for resetting the new password.
        progressBarForResetPwd.setVisibility(View.GONE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Reset link has been sent to your email.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ForgotPasswordActivity.this,"Error! Reset link is not sent. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}