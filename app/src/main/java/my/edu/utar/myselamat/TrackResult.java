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
    TextView a,b,c;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_result);
        a=findViewById(R.id.textView29);
        b=findViewById(R.id.textView30);
        c=findViewById(R.id.textView31);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");

        databaseReference = FirebaseDatabase.getInstance().getReference("Hotspot2");
        databaseReference.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String addr = snapshot.child("addr").getValue().toString();
                //String lat = snapshot.child("lat").getValue().toString();
                //String longti = snapshot.child("long").getValue().toString();
                String cases1 = snapshot.child("case").getValue().toString();
                String risk1 = snapshot.child("risk").getValue().toString();


                a.setText(addr);
                b.setText(cases1);
                c.setText(risk1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}