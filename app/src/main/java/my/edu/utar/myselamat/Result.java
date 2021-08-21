package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    TextView textView;
    String[] Location ={"Kampar","Hospital Kampar","Sitiawan","Batu Pahat"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //String[] Location = getResources().getStringArray(R.array.Location);

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

        //String str = autotv.getText().toString();
    }
}