package my.edu.utar.myselamat;

import android.widget.EditText;

public class UserActivity {
    public String username, ic, addressLine1, addressLine2, postalCode, phoneNo, email, state;

    public UserActivity() {
    }

    public UserActivity(String username, String ic, String addressLine1, String addressLine2, String postalCode, String phoneNo, String email, String state) {
        this.username = username;
        this.ic = ic;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        this.phoneNo = phoneNo;
        this.email = email;
        this.state = state;
    }


}
