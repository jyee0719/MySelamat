package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FacilityList extends AppCompatActivity {

    ExpandableListView expandableListView;
    HashMap<String,List<String>> stateItem;
    ArrayList<String> stateGroup;
    DatabaseReference databaseReference;
    StateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_list);

        stateItem = new HashMap<>();

        expandableListView = findViewById(R.id.expandList);
        //add value into group list


        ArrayList<String> johor = new ArrayList<>();
        johor.add("Hospital Sultanah Aminah");
        stateItem.put("Johor", johor);

        ArrayList<String> kedah = new ArrayList<>();
        kedah.add("Hospital Sultanah Bahiyah");
        kedah.add("Hospital Sultan Abdul Halim");
        kedah.add("Hospital Kulim");
        kedah.add("Hospital Sultanah Maliha");
        stateItem.put("Kedah", kedah);

        ArrayList<String> kelantan = new ArrayList<>();
        kelantan.add("Hospital Raja Perempuan Zainab 2");
        kelantan.add("Hospital Sultan Ismail Petra");
        kelantan.add("Hospital Tumpat");
        stateItem.put("Kelantan", kelantan);

        ArrayList<String> kl = new ArrayList<>();
        kl.add("Hospital Kuala Lumpur");
        stateItem.put("Kuala Lumpur", kl);

        ArrayList<String> melaka = new ArrayList<>();
        melaka.add("Hospital Melaka");
        stateItem.put("Melaka", melaka);

        ArrayList<String> n9 = new ArrayList<>();
        n9.add("Hospital Tuanku Ja‘afar");
        stateItem.put("Negeri Sembilan", n9);

        ArrayList<String> pahang = new ArrayList<>();
        pahang.add("Hospital Tengku Ampuan Afzan");
        stateItem.put("Pahang", pahang);

        ArrayList<String> perak = new ArrayList<>();
        perak.add("Hospital Raja Permaisuri Bainun");
        stateItem.put("Perak", perak);

        ArrayList<String> perlis = new ArrayList<>();
        perlis.add("Hospital Tuanku Fauziah");
        stateItem.put("Perlis", perlis);

        ArrayList<String> penang = new ArrayList<>();
        penang.add("Hospital Pulau Pinang");
        stateItem.put("Pulau Pinang", penang);

        ArrayList<String> selangor = new ArrayList<>();
        selangor.add("Hospital Sungai Buloh");
        stateItem.put("Selangor", selangor);

        ArrayList<String> ter = new ArrayList<>();
        ter.add("Hospital Sultanah Nur Zahirah");
        stateItem.put("Terengganu", ter);

        StateAdapter adapter = new StateAdapter(stateItem);
        expandableListView.setAdapter(adapter);
    }


}