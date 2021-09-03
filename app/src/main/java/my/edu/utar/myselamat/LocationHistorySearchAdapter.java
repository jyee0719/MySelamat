package my.edu.utar.myselamat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class LocationHistorySearchAdapter extends FirebaseRecyclerAdapter<LocationCheckin, LocationHistorySearchAdapter.myViewHolder> {

    public LocationHistorySearchAdapter( FirebaseRecyclerOptions<LocationCheckin> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(LocationHistorySearchAdapter.myViewHolder holder, int position, LocationCheckin locationCheckin) {
        //set text display details
        holder.location.setText(locationCheckin.getCheckinlocation());
        holder.date.setText("Date: " + locationCheckin.getCheckindate());
        holder.time.setText("Time:" + locationCheckin.getCheckintime());
    }

    @Override
    public LocationHistorySearchAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_location_history2, parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView location, date, time;

        public myViewHolder(View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.CVcheckinto);
            date = itemView.findViewById(R.id.CVdate);
            time = itemView.findViewById(R.id.CVtime);
        }
    }
}
