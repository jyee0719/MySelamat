package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    TextView textView;
    ArrayAdapter<String> adapter;
    ImageButton clearbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String[] Location = getResources().getStringArray(R.array.locations);

        autoCompleteTextView = findViewById(R.id.autotv);
        textView=findViewById(R.id.tv);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Location);

        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(adapter.getItem(position));
                String location = String.valueOf(textView.getText());
                Intent intent = new Intent(Result.this, Hotspot.class);
                intent.putExtra("location",location);
                startActivity(intent);
            }
        });

        clearbutton=findViewById(R.id.clear);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.setText(null);
            }
        });

    }
}