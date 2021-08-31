package my.edu.utar.myselamat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.time.Instant;
import java.util.ArrayList;

public class VaccineAdapter extends BaseAdapter {

    private final ArrayList<Vaccine> vaccineArrayList;
    private final Context context;

    public VaccineAdapter(ArrayList<Vaccine> vaccineArrayList, Context context){
        this.vaccineArrayList = vaccineArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.vaccineArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //set the view to single_vaccine.xml
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.single_vaccine, null);

        //Declaration
        TextView type = convertView.findViewById(R.id.vname);
        TextView desc = convertView.findViewById(R.id.vdesc);
        ImageView image = convertView.findViewById(R.id.vimageview);

        //get the item in the array list and set the data
        Vaccine vaccine = vaccineArrayList.get(position);
        type.setText(vaccine.getVtype());
        desc.setText(vaccine.getVinfo());

        //set the image using url through Glide
        Glide.with(context)
                .load(vaccine.getVimage())
                .into(image);

        // return view
        return convertView;
    }

}
