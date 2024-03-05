package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        EditText username1, password1;
        DBHelper DB;

        Button Log_in_butt = findViewById(R.id.Log_in_button);
        Button Sign_up_activity_button = findViewById(R.id.Sign_up_button);

        DB = new DBHelper(this);

        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);

        Sign_up_activity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Open_Sign_up_page();}
        });

        Log_in_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username1.getText().toString();
                String pass = password1.getText().toString();

                if(username.equals("") || pass.equals("")){
                    Toast.makeText(user_login.this, "Please enter required data", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkUserpass = DB.checkUserNamePassword(username, pass);
                    if(checkUserpass == true){
                        Toast.makeText(user_login.this, "Log in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home_page.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(user_login.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    /*public void Home_Page(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }*/
    public void Open_Sign_up_page(){
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }
}