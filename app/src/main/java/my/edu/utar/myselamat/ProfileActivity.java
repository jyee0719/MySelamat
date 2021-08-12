package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfileActivity extends Fragment {

    private Button register;

    public ProfileActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.activity_profile, container, false);

        View view = inflater.inflate(R.layout.activity_profile, container, false);

        register = (Button) view.findViewById(R.id.register);

//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(this, ThankYou.class));
//                Intent n = new Intent(getActivity(), ThankYou.class);
//                n.putExtra("some","some data");
//                startActivity(n);

        //ThankYou ty = new ThankYou();
//
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment1, new ThankYou());
//                transaction.commit();
        //           }
        //       });

//

//
//        /*Button register = (Button)findViewById(R.id.button7);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t();
            }
        });
        return view;
    }

    public void t(){
        Intent intent = new Intent(getContext(), ThankYou.class);
        startActivity(intent);
    }

}