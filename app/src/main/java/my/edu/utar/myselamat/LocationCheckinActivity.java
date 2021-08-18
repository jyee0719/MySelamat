package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocationCheckinActivity extends AppCompatActivity {

    EditText checkinlocation;
    Button checkindate;
    EditText checkintime;
    Button submit;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_checkin);

        checkinlocation = findViewById(R.id.checkinlocation);
        checkindate = findViewById(R.id.checkindate);
        checkintime = findViewById(R.id.checkintime);
        submit = findViewById(R.id.submit);
        checkindate.setText(getTodaysDate());

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = makeDateString(day, month, year);
                checkindate.setText(date);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        checkintime.setText(getCurrentTime());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LocationCheckinActivity.this, "Check In Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LocationCheckinActivity.this, HomeActivity.class);
                startActivity(i);
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
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }
}