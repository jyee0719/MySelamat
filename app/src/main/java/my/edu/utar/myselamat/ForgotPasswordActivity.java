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

        // Button to reset password
        btn_resetPwd.setOnClickListener(v -> {
            resetPassword();
        });
    }

    private void resetPassword(){
        String email = emailForResetPwd.getText().toString().trim();

        // If email is empty, then it will pop out "full name is required".
        if(email.isEmpty()){
            emailForResetPwd.setError("Full name is required!");
            emailForResetPwd.requestFocus();
            return;
        }

        // Validate the email pattern
        // If the email pattern is not correct, then it will pop out "Please provide valid email".
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailForResetPwd.setError("Please provide valid email!");
            emailForResetPwd.requestFocus();
            return;
        }

        // If the process of resetting password is successful, system will send them a link via email.
        // Then, user will need to enter that link and it will appear a pop up window for resetting the new password.
        // If the process of resetting password is unsuccessful, user is needed to re-enter their email again.
        progressBarForResetPwd.setVisibility(View.GONE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset your password!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ForgotPasswordActivity.this,"Try again! Something wrong happened!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}