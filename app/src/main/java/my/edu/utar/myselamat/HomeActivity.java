package my.edu.utar.myselamat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        Button location_checkin_button,vaccineType,sop_button,hotspot_button,health_status_button,register_vaccine_button,check_vaccine_button,locate_health_button, essential_button;
        ImageView profile,history,chat;

        if(firebaseUser == null){
            startActivity(new Intent(HomeActivity.this, UserLoginActivity.class));
        }

        location_checkin_button = findViewById(R.id.location_checkin_button);
        sop_button = findViewById(R.id.sop_button);
        hotspot_button = findViewById(R.id.hotspot_button);
        health_status_button = findViewById(R.id.health_status_button);
        register_vaccine_button = findViewById(R.id.register_vaccine_button);
        check_vaccine_button = findViewById(R.id.check_vaccine_button);
        locate_health_button = findViewById(R.id.locate_health_button);
        profile = findViewById(R.id.profile);
        history = findViewById(R.id.history);
        essential_button = findViewById(R.id.essential_button);
        vaccineType = findViewById(R.id.vaccineType_button);
        chat = findViewById(R.id.chat);

        location_checkin_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LocationCheckinActivity.class);
            startActivity(intent);
        });

        sop_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, sopActivity.class);
            startActivity(intent);
        });

        hotspot_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Result.class);
            startActivity(intent);
        });

        health_status_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HealthStatusActivity.class);
            startActivity(intent);
        });

        register_vaccine_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, VaccineRegistration.class);
            startActivity(intent);
        });

        check_vaccine_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, VaccinationStatus.class);
            startActivity(intent);
        });

        locate_health_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FacilityList.class);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        history.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LocationHistoryActivity.class);
            startActivity(intent);
        });

        essential_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Essentials.class);
            startActivity(intent);
        });

        vaccineType.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, VaccineInfo.class);
            startActivity(intent);
        });

        String number = "+60127826118";
        String text = "Hi, I would like to learn more about MySelamat application.";

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = isAppInstalled("com.whatsapp");

                if(installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone="+number+"&text="+text));
                    startActivity(intent);
                }
                else {
                    Toast.makeText(HomeActivity.this, "Whatsapp is not installed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isAppInstalled(String s) {
        PackageManager packageManager = getPackageManager();
        boolean is_installed;

        try{
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            is_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            is_installed = false;
            e.printStackTrace();
        }
        return is_installed;
    }
}
