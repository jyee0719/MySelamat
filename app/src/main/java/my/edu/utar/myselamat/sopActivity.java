package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class sopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sop);
    }

    public void openBrowser(View view){
        // Get url from tag
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        // Pass the url to intent data
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }

    public void noInfoUpload(View view){
        Toast.makeText(this, "No any information uploaded in Fasa 3 and Fasa 4!!", Toast.LENGTH_LONG).show();
    }
}