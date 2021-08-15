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

        // Disable btn_submit at start
        btn_submit.setEnabled(false);

        // Create ArrayList
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

        boolean[] check_flag = {false, false, false, false, false, false};

        for(int rid = 0; rid < radioGroupList.size(); rid++){

            int ind = rid;

            radioGroupList.get(rid).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    check_flag[ind] = true;

                    boolean all_flag = true;

                    for(int i = 0;i < 6; i++) {
                        if (!check_flag[i])
                            all_flag = false;
                    }

                    btn_submit.setEnabled(all_flag);
                }
            });
        }


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = 0;

                int[] add_id = { R.id.rb1_no, R.id.rb3_no, R.id.rb5_no, R.id.rb7_no, R.id.rb9_no, R.id.rb11_no};

                for(int id : add_id) {
                    RadioButton rb = (RadioButton) findViewById(id);
                    if(rb.isChecked()) score++;
                    // score += (rb.isChecked()) ? 1 : 0;
                }

                displayResult(score);

                //
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                int wrapper[] = {score};
                if(!uid.isEmpty()){
                    Log.i("User Id: ", uid);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            UserActivity ua = snapshot.getValue(UserActivity.class);
                            ua.setHealthStatus((wrapper[0] == 6) ? "Low Risk" : "High Risk");
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
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthStatusQuestionActivity.this, HomeActivity.class));
            }
        });
    }

    private void displayResult(int score){
        String message = (score == 6) ? "Low Risk Status" : "High Risk Status";

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
