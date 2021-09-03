package my.edu.utar.myselamat;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.os.Looper.getMainLooper;

public class LocationHistoryRecyclerAdapter extends RecyclerView.Adapter<LocationHistoryRecyclerAdapter.ViewHolder> {

    private ArrayList<LocationCheckin> locationCheckinlist;
    private LocationHistoryActivity activity;

    public LocationHistoryRecyclerAdapter(ArrayList<LocationCheckin> locationCheckin, LocationHistoryActivity activity) {
        this.locationCheckinlist = locationCheckin;
        this.activity = activity;
    }

    @Override
    public LocationHistoryRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_location_history2, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView location, date, time;

        public ViewHolder(View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.CVcheckinto);
            date = itemView.findViewById(R.id.CVdate);
            time = itemView.findViewById(R.id.CVtime);
        }
    }

    @Override
    public void onBindViewHolder(LocationHistoryRecyclerAdapter.ViewHolder holder, int position) {
        LocationCheckin locationCheckin = locationCheckinlist.get(position);
        final Handler handler = new Handler(getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //set text to display details
                        holder.location.setText(locationCheckin.getCheckinlocation());
                        holder.date.setText("Date: " + locationCheckin.getCheckindate());
                        holder.time.setText("Time: " + locationCheckin.getCheckintime());
                    }
                });
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public int getItemCount() {
        return locationCheckinlist.size();
    }

    public Context getContext() {
        return activity;
    }
}

