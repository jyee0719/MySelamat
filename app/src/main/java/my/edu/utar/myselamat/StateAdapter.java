package my.edu.utar.myselamat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class StateAdapter extends BaseExpandableListAdapter {
    Context context;
    String[] MstateGroup;
    HashMap<String,List<String>> MstateItem;

    public StateAdapter(HashMap<String, List<String>> stateItem){
        MstateItem = stateItem;
        MstateGroup = MstateItem.keySet().toArray(new String[0]);
    }

    public StateAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return MstateGroup.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return MstateItem.get(MstateGroup[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return MstateGroup[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return MstateItem.get(MstateGroup[groupPosition]).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition*childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //String group = (String) getGroup(groupPosition);
        if (convertView == null){
            //LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.list_parent);
        textView.setText(String.valueOf(getGroup(groupPosition)));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //String child = (String) getChild(groupPosition, childPosition);
        if(convertView == null){
            //LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.list_child);
        textView.setText(String.valueOf(getChild(groupPosition,childPosition)));

        //action when the child in the expandable list in clicked
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display toast message when item is clicked
                Toast.makeText(parent.getContext(), String.valueOf(getChild(groupPosition,childPosition)), Toast.LENGTH_SHORT).show();
                String hospital = String.valueOf(getChild(groupPosition,childPosition));
                //if the child clicked is "Hospital Tuanku Fauziah", its latitude and longitude value will be passed to the LocateActivity
                if(hospital.equals("Hospital Tuanku Fauziah")){
                    String latPerlis = String.valueOf(6.4409);
                    String lngPerlis = String.valueOf(100.1914);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latPerlis);
                    intent.putExtra("lng", lngPerlis);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Sultanah Aminah")){
                    String latJohor = String.valueOf(1.4585);
                    String lngJohor = String.valueOf(103.7460);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latJohor);
                    intent.putExtra("lng", lngJohor);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Sultanah Bahiyah")){
                    String latKedah1 = String.valueOf(6.1488);
                    String lngKedah1 = String.valueOf(100.4064);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latKedah1);
                    intent.putExtra("lng", lngKedah1);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Sultan Abdul Halim")){
                    String latKedah2 = String.valueOf(5.6696);
                    String lngKedah2 = String.valueOf(100.5174);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latKedah2);
                    intent.putExtra("lng", lngKedah2);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Kulim")){
                    String latKedah3 = String.valueOf(5.3931);
                    String lngKedah3 = String.valueOf(100.5730);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latKedah3);
                    intent.putExtra("lng", lngKedah3);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Sultanah Maliha")){
                    String latKedah4 = String.valueOf(6.3248);
                    String lngKedah4 = String.valueOf(99.7984);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latKedah4);
                    intent.putExtra("lng", lngKedah4);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Raja Perempuan Zainab 2")){
                    String latKelantan1 = String.valueOf(6.1253);
                    String lngKelantan1 = String.valueOf(102.2457);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latKelantan1);
                    intent.putExtra("lng", lngKelantan1);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Sultan Ismail Petra")){
                    String latKelantan2 = String.valueOf(5.4963);
                    String lngKelantan2 = String.valueOf(102.2228);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latKelantan2);
                    intent.putExtra("lng", lngKelantan2);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Tumpat")){
                    String latKelantan3 = String.valueOf(6.1897);
                    String lngKelantan3 = String.valueOf(102.1573);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latKelantan3);
                    intent.putExtra("lng", lngKelantan3);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Kuala Lumpur")){
                    String latKL = String.valueOf(3.1719);
                    String lngKL = String.valueOf(101.7012);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latKL);
                    intent.putExtra("lng", lngKL);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Melaka")){
                    String latMelaka = String.valueOf(2.21729);
                    String lngMelaka = String.valueOf(102.2613);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latMelaka);
                    intent.putExtra("lng", lngMelaka);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Tuanku Ja‘afar")){
                    String latN9 = String.valueOf(2.7098);
                    String lngN9 = String.valueOf(101.9448);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latN9);
                    intent.putExtra("lng", lngN9);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Tengku Ampuan Afzan")){
                    String latPahang = String.valueOf(3.8009);
                    String lngPahang = String.valueOf(103.3215);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latPahang);
                    intent.putExtra("lng", lngPahang);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Raja Permaisuri Bainun")){
                    String latPerak = String.valueOf(4.6039);
                    String lngPerak = String.valueOf(101.0902);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latPerak);
                    intent.putExtra("lng", lngPerak);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Tuanku Fauziah")){
                    String latPerlis = String.valueOf(6.4409);
                    String lngPerlis = String.valueOf(100.1914);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latPerlis);
                    intent.putExtra("lng", lngPerlis);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Pulau Pinang")){
                    String latPenang = String.valueOf(5.4171);
                    String lngPenang = String.valueOf(100.3114);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latPenang);
                    intent.putExtra("lng", lngPenang);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Sungai Buloh")){
                    String latSelangor = String.valueOf(3.2196);
                    String lngSelangor = String.valueOf(101.5832);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latSelangor);
                    intent.putExtra("lng", lngSelangor);
                    v.getContext().startActivity(intent);
                } else if(hospital.equals("Hospital Sultanah Nur Zahirah")){
                    String latTer = String.valueOf(5.3239);
                    String lngTer = String.valueOf(103.1507);

                    Intent intent = new Intent(v.getContext(), LocateActivity.class);
                    intent.putExtra("name", hospital);
                    intent.putExtra("lat", latTer);
                    intent.putExtra("lng", lngTer);
                    v.getContext().startActivity(intent);
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
