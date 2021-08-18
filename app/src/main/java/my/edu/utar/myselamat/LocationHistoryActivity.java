package my.edu.utar.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LocationHistoryActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    SearchView searchView;
    RecyclerView historyRecyclerView;
    LocationHistoryRecyclerAdapter adapter;
    TextView nolocationhistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_history);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child(userID).child("Location");


        searchView = findViewById(R.id.search);
        nolocationhistory = findViewById(R.id.nolocationhistory);
        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Handler handler = new Handler(getMainLooper());
        final ArrayList<LocationCheckin> locationCheckinlist = new ArrayList<>();

        //Runnable: get data from firebase
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                reference.orderByChild("checkindate").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            LocationCheckin list = ds.getValue(LocationCheckin.class);
                            locationCheckinlist.add(list);
                        }

                        //handler
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(locationCheckinlist.isEmpty()) {
                                    nolocationhistory.setVisibility(View.VISIBLE);
                                } else {
                                    nolocationhistory.setVisibility(View.GONE);
                                }
                                adapter = new LocationHistoryRecyclerAdapter(locationCheckinlist, LocationHistoryActivity.this);
                                historyRecyclerView.setAdapter(adapter);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Toast.makeText(LocationHistoryActivity.this, "Unable to get data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();


    }
}