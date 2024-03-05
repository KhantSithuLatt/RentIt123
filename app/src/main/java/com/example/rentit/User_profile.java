package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        EditText UsernameUser, PhoneNumberUser, AddressUser, NRCNumberUser, LiscenNumberUser;
        Button UserProfileFinsihed_but;

        UsernameUser = findViewById(R.id.usernameUser);
        PhoneNumberUser = findViewById(R.id.phoneNumberUser);
        AddressUser = findViewById(R.id.AddressUser);
        NRCNumberUser = findViewById(R.id.NRCNumberUser);
        LiscenNumberUser = findViewById(R.id.LiscenNumberUser);
        UserProfileFinsihed_but = findViewById(R.id.UserProfile_finished_but);
        DBHelper DB = new DBHelper(this);

        UserProfileFinsihed_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = UsernameUser.getText().toString();
                String Address = AddressUser.getText().toString();
                String phoneNumber = PhoneNumberUser.getText().toString();
                String NRCNumber = NRCNumberUser.getText().toString();
                String LiscenNumber = LiscenNumberUser.getText().toString();



                if(Username.equals("") ||  Address.equals("") || NRCNumber.equals("") || LiscenNumber.equals("")){
                    Toast.makeText(User_profile.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isValidPhoneNumber(String.valueOf(phoneNumber))) {
                        Toast.makeText(User_profile.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                        boolean checkuserExists = DB.checkUserExists(Username);
                        if(checkuserExists == true){
                            boolean insert = DB.insertUserProfileData(Username, phoneNumber, Address, NRCNumber, LiscenNumber);
                            if(insert == false){
                                Toast.makeText(User_profile.this, "User Data Updated Failed", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(User_profile.this, "User Data Updated Successfully", Toast.LENGTH_SHORT).show();

                                // Pass the user data to the next activity
                                Intent intent = new Intent(User_profile.this, UserProfileDisplay.class);
                                intent.putExtra("username", Username);
                                intent.putExtra("phoneNumber", phoneNumber);
                                intent.putExtra("address", Address);
                                intent.putExtra("nrcNumber", NRCNumber);
                                intent.putExtra("liscenNumber", LiscenNumber);
                                startActivity(intent);
                            }
                        }else{
                            Toast.makeText(User_profile.this, "User does not Exist. Please Sign Up again.", Toast.LENGTH_SHORT).show();
                        }

                }
            }
        });
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression to match a 10-digit phone number
        String regex = "\\d{11}";
        return phoneNumber.matches(regex);
    }
}