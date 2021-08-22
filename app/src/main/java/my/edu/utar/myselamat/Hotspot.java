package my.edu.utar.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class Hotspot extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;
    DatabaseReference databaseReference;
    Button button_track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot);

        Intent intent=getIntent();
        String location = intent.getStringExtra("location");

        button_track=findViewById(R.id.button2);
        button_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name1 = location;

                Intent intent = new Intent(Hotspot.this, TrackResult.class);
                intent.putExtra("location",location);
                startActivity(intent);
            }
        });


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
        map = googleMap;
        LatLng sydney = new LatLng(4.313581049356431, 101.14234690922714);
        map.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Intent intent=getIntent();
        String location = intent.getStringExtra("location");
        databaseReference = FirebaseDatabase.getInstance().getReference("Hotspot2");
        databaseReference.child(location).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LatLng latLng = new LatLng(
                        snapshot.child("lat").getValue(Long.class),
                        snapshot.child("long").getValue(Long.class));

                map.addMarker(new MarkerOptions().position(latLng)
                .title(location));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    @Override
//    protected void onResume(){
//        super.onResume();
//        if(map!=null){
//            map.clear();
//        }
//    }
}