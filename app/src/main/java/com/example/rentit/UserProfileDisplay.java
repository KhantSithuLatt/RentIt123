package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfileDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_display);

        //getting user data record
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String address = intent.getStringExtra("address");
        String nrcNumber = intent.getStringExtra("nrcNumber");
        String liscenNumber = intent.getStringExtra("liscenNumber");

        TextView Username = findViewById(R.id.UserProfileDisUsername);
        TextView PhoneNumber = findViewById(R.id.UserProfileDisPhoneNo);
        TextView Address = findViewById(R.id.UserProfileDisAddress);
        TextView NRC = findViewById(R.id.UserProfileDisNRC);
        TextView Liscen = findViewById(R.id.UserProfileDisLiscen);
        Button Update = findViewById(R.id.UpdateUserProfileBut);


        // Setting the obtained values to the TextViews
        Username.setText("Username: " + username);
        PhoneNumber.setText("Phone Number: " + phoneNumber);
        Address.setText("Address: " + address);
        NRC.setText("NRC Number: " + nrcNumber);
        Liscen.setText("License Number: " + liscenNumber);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), User_profile.class);
                startActivity(intent);
                finish();
            }
        });
    }
}