package my.edu.utar.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HealthStatusQuestionActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btn_submit;
    private Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_status_question);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        // Disable submit button at the beginning
        btn_submit.setEnabled(false);

        // Create ArrayList for all radio group
        ArrayList<RadioGroup> radioGroupList = new ArrayList<RadioGroup>();

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.rGroup1);
        radioGroupList.add(rg1);

        RadioGroup rg2 = (RadioGroup) findViewById(R.id.rGroup2);
        radioGroupList.add(rg2);

        RadioGroup rg3 = (RadioGroup) findViewById(R.id.rGroup3);
        radioGroupList.add(rg3);

        RadioGroup rg4 = (RadioGroup) findViewById(R.id.rGroup4);
        radioGroupList.add(rg4);

        RadioGroup rg5 = (RadioGroup) findViewById(R.id.rGroup5);
        radioGroupList.add(rg5);

        RadioGroup rg6 = (RadioGroup) findViewById(R.id.rGroup6);
        radioGroupList.add(rg6);

        // Check all the radio group have selected one of the button and the submit button will be enabled.
        boolean[] check_flag = {false, false, false, false, false, false};

        for(int rid = 0; rid < radioGroupList.size(); rid++){

            int ind = rid;

            radioGroupList.get(rid).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // Set a flag is true for checking each radio group.
                    check_flag[ind] = true;

                    // Set all flag will become true and the submit button will be enabled.
                    boolean all_flag = true;

                    // The radio group is checked one by one
                    for(int i = 0;i < 6; i++) {
                        // If one of the radio group is checked as no button selected, the flag will become false.
                        if (!check_flag[i])
                            // If one of the flag become false, the submit button will still be disabled.
                            all_flag = false;
                    }

                    // If all flag is true, the submit button will be enabled.
                    btn_submit.setEnabled(all_flag);
                }
            });
        }


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = 0;

                int[] add_id = { R.id.rb1_no, R.id.rb3_no, R.id.rb5_no, R.id.rb7_no, R.id.rb9_no, R.id.rb11_no};

                // If the specified button is selected, the score will be kept on adding.
                for(int id : add_id) {
                    RadioButton rb = (RadioButton) findViewById(id);
                    if(rb.isChecked()) score++;
                }

                displayResult(score);

                // Get the uid of current user
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                int wrapper[] = {score};
                if(!uid.isEmpty()){
                    Log.i("User Id: ", uid);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            // Get UserActivity object and get value for updating
                            UserActivity ua = snapshot.getValue(UserActivity.class);
                            // Get the health status result for updating
                            ua.setHealthStatus((wrapper[0] == 6) ? "Low Risk" : "High Risk");
                            // When calling updateChildren(), it will update the child values by specifying a path for the key.
                            databaseReference.child(uid).updateChildren(ua.toMap());
                            Log.i("Database: ", "Update is Successful");
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    };

                    databaseReference.child(uid).addValueEventListener(eventListener);

                }else{
                    Log.i("Error: ", "User Id is Null");
                }
                startActivity(new Intent(HealthStatusQuestionActivity.this, HomeActivity.class));
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    // If the user gets the full score, the health status will be updated as low risk status.
    // If the user cannot get the full score, the health status will be updated as high risk status.
    private void displayResult(int score){
        String message = (score == 6) ? "Low Risk Status" : "High Risk Status";

        Toast.makeText(this, "Please go to user profile to check the updated health status!!", Toast.LENGTH_SHORT).show();
    }

}
