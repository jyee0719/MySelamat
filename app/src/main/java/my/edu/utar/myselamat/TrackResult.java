package my.edu.utar.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TrackResult extends AppCompatActivity {
    TextView addr,cases,risk;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_result);
        addr=findViewById(R.id.addr);
        cases=findViewById(R.id.cases);
        risk=findViewById(R.id.risk);

        Intent intent=getIntent();
        String name=intent.getStringExtra("location");

        databaseReference = FirebaseDatabase.getInstance().getReference("Hotspot2");
        databaseReference.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String addr1 = snapshot.child("addr").getValue().toString();
                String cases1 = snapshot.child("case").getValue().toString();
                String risk1 = snapshot.child("risk").getValue().toString();


                addr.setText(addr1);
                cases.setText(cases1);
                risk.setText(risk1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}