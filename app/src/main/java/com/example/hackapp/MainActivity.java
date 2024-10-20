package com.example.hackapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button; //manual import
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText u_input, p_input;
     Button loginBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        u_input = findViewById(R.id.username_input);
        p_input = findViewById(R.id.password_input);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = u_input.getText().toString();
                String password = p_input.getText().toString();

                //login credential
                Log.i("Login Credential",
                        "Username:" + username +
                                "\nPassword: " + password);

                setContentView(R.layout.activity_home);
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openDriver(View view){
        setContentView(R.layout.activity_details_driver);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button enter = findViewById(R.id.driver_enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText start = findViewById(R.id.driver_start);
                EditText destination = findViewById(R.id.driver_destination);
                EditText time = findViewById(R.id.driver_time);
                EditText date = findViewById(R.id.driver_date);
                Switch repeat = findViewById(R.id.driver_repeat);
                Driver match = findDriver(start.getText().toString(), destination.getText().toString(), time.getText().toString(),date.getText().toString(), repeat.isActivated());
                confirmDriver();
            }
        });
    }
    public void openRider(View view){
        setContentView(R.layout.rider_details);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button enter = findViewById(R.id.rider_enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText start = findViewById(R.id.rider_start);
                EditText destination = findViewById(R.id.rider_destination);
                EditText time = findViewById(R.id.rider_time);
                EditText date = findViewById(R.id.rider_date);
                Switch repeat = findViewById(R.id.rider_repeat);
                Driver match = findDriver(start.getText().toString(), destination.getText().toString(), time.getText().toString(),date.getText().toString(), repeat.isActivated());
                matchScreen();
            }
        });
    }

    //TODO make request to server to find best driver
    public Driver findDriver(String start, String destination, String time, String date, boolean repeat){

        return new Driver();
    }

    public void matchScreen(){
        setContentView(R.layout.match);

    }

    public void openHome(View view){
        setContentView(R.layout.activity_home);
    }

    public void confirmDriver(){
        setContentView(R.layout.activity_confirm_driver);
    }
}