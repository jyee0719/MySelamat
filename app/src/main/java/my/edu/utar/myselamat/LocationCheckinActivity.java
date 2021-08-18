package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocationCheckinActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    EditText locationET;
    Button dateET;
    TextView timeET;
    Button submit;
    DatePickerDialog datePickerDialog;
    int selecthour, selectminute;

    String checkinlocation = "";
    String checkindate = "";
    String checkintime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_checkin);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        locationET = findViewById(R.id.checkinlocation);
        dateET = findViewById(R.id.checkindate);
        timeET = findViewById(R.id.checkintime);
        submit = findViewById(R.id.submit);
        dateET.setText(getTodaysDate());

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = makeDateString(day, month, year);
                dateET.setText(date);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        timeET.setText(getCurrentTime());
        timeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time picler dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        LocationCheckinActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                selecthour = hourOfDay;
                                selectminute = minute;

                                //Store hour and minute in string
                                String time = selecthour + ":" + selectminute;

                                //Initialize 24 hours time format
                                SimpleDateFormat f24hour = new SimpleDateFormat("HH:mm");

                                try {
                                    Date date = f24hour.parse(time);
                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");

                                    //Set selected time on textview
                                    timeET.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        },12,0,false);
                //Set Transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Displayed previous selected time
                timePickerDialog.updateTime(selecthour,selectminute);
                //show dialog
                timePickerDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetails();
            }
        });
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int year = calendar.get(Calendar.YEAR);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return day + "/" + month + "/" + year;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());
    }

    private void addDetails() {

        checkinlocation = locationET.getText().toString().trim();
        checkindate = dateET.getText().toString().trim();
        checkintime = timeET.getText().toString().trim();

        if(checkinlocation.isEmpty()) {
            locationET.setError("Location is required.");
            locationET.requestFocus();
            Toast.makeText(this, "Incomplete Details", Toast.LENGTH_SHORT).show();
        }
        if(checkintime.isEmpty()) {
            timeET.setError("Time is required.");
            timeET.requestFocus();
            Toast.makeText(this, "Incomplete Details", Toast.LENGTH_SHORT).show();
        }
        String key = reference.push().getKey();
        LocationCheckin locationCheckin = new LocationCheckin(checkinlocation, checkindate, checkintime);
        reference.child(key).setValue(locationCheckin);

        Toast.makeText(LocationCheckinActivity.this, "Check In Successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LocationCheckinActivity.this, HomeActivity.class);
        startActivity(i);
    }
}