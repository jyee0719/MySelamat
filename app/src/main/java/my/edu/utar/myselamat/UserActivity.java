package my.edu.utar.myselamat;

import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class UserActivity {
    private String username,
            ic,
            addressLine1,
            addressLine2,
            postalCode,
            phoneNo,
            email,
            state,
            healthStatus;

    public UserActivity() {
        this("", "", "", "", "", "", "", "");
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
        this.healthStatus = "Low Risk";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getUsername() {
        return username;
    }

    public String getIc() {
        return ic;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public String getState() {
        return state;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("ic", ic);
        result.put("addressLine1", addressLine1);
        result.put("addressLine2", addressLine2);
        result.put("postalCode", postalCode);
        result.put("phoneNo", phoneNo);
        result.put("email", email);
        result.put("state", state);
        result.put("healthStatus", healthStatus);
        return result;
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "username='" + username + '\'' +
                ", ic='" + ic + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", healthStatus='" + healthStatus + '\'' +
                '}';
    }
}
