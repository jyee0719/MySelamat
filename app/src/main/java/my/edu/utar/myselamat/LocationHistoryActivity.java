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

import com.firebase.ui.database.FirebaseRecyclerOptions;
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

    RecyclerView historyRecyclerView;
    LocationHistoryRecyclerAdapter adapter;
    TextView nolocationhistory;
    LinearLayoutManager linearLayoutManager;
    LocationHistorySearchAdapter locationHistorySearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_history);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userID).child("Location");

        nolocationhistory = findViewById(R.id.nolocationhistory);
        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        historyRecyclerView.setLayoutManager(linearLayoutManager);


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

        FirebaseRecyclerOptions<LocationCheckin> options = new FirebaseRecyclerOptions.Builder<LocationCheckin>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Users").child(userID).child("Location"), LocationCheckin.class)
                .build();

        locationHistorySearchAdapter = new LocationHistorySearchAdapter(options);
        historyRecyclerView.setAdapter(locationHistorySearchAdapter);

        SearchView searchView = findViewById(R.id.historysearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                Toast.makeText(LocationHistoryActivity.this, "Searching...", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                Toast.makeText(LocationHistoryActivity.this, "Searching...", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void search(String s) {

        FirebaseRecyclerOptions<LocationCheckin> options = new FirebaseRecyclerOptions.Builder<LocationCheckin>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Users").child(userID).child("Location")
                        .orderByChild("checkinlocation").startAt(s).endAt(s + "~"), LocationCheckin.class)
                .build();

        locationHistorySearchAdapter = new LocationHistorySearchAdapter(options);
        locationHistorySearchAdapter.startListening();
        historyRecyclerView.setAdapter(locationHistorySearchAdapter);
    }
}