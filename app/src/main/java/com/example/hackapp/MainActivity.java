package com.example.hackapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button; //manual import
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String usern;
    EditText u_input, p_input;
     Button loginBtn;

     private Driver[] drivers= new Driver[20];
     int dindex ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //example driver data
        this.dindex = 0;
        drivers[dindex] = new Driver("bob@gmail.com", "1739 N High St, Columbus, OH 43210", "1350 N High St, Columbus, OH 43201", "09:00", "10/20/2024",true);
        dindex++;
        u_input = findViewById(R.id.username_input);
        p_input = findViewById(R.id.password_input);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = u_input.getText().toString();
                usern = username;
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
                Driver driver = new Driver(usern, start.getText().toString(), destination.getText().toString(), time.getText().toString(), date.getText().toString(), repeat.isActivated());
                if(dindex < 20){
                    drivers[dindex] = driver;
                    dindex++;
                }
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
                int cost = score(match, start.getText().toString(), destination.getText().toString());
                matchScreen(match, cost);

            }
        });
    }

    //TODO make request to server to find best driver
    private Driver findDriver(String start, String destination, String time, String date, boolean repeat){
        int lowest = Integer.MAX_VALUE;
        Driver best = drivers[0];
        for(int i = 0 ; i < dindex; i++){

            int score = score(drivers[i], start,destination);
            if(score < lowest)
            {
                lowest =score;
                best = drivers[i];
            }
        }

        return best;
    }

    public void matchScreen(Driver match, int cost){
        setContentView(R.layout.match);

        @SuppressLint({"WrongViewCast", "MissingInflatedId", "LocalSuppress"}) TextView user = findViewById(R.id.match_user);
        user.setText("User: "+ match.name);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView price = findViewById(R.id.match_price);
        price.setText("Price: " + cost);


    }

    public void openHome(View view){
        setContentView(R.layout.activity_home);
    }

    public void confirmDriver(){
        setContentView(R.layout.activity_confirm_driver);
    }

    private int score(Driver driver, String start, String destination){
        int score = 0;
        try {
            score = (int)(4*(distance(driver.start,start) + distance(driver.destination,destination)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return score;
    }

    private float distance(String s, String d)throws IOException{
        String start = s.replaceAll(" ", "%");
        String end  = d.replaceAll(" ", "%");

        /*URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?destinations=" + start+ "&origins=" + end + "&units=imperial&key=AIzaSyCEBM28wNUArLBDSBc_yp754pGSwhfLbUI");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        float distance = 0;
        while ((line = br.readLine()) != null) {
            if(line.contains("text")){
                int i  = line.indexOf(":")+3;
                int i2 = line.indexOf("mi")-1;
                String sub = line.substring(i,i2);
                distance = Float.parseFloat(sub);
                break;
            }
        }*/
        return 4;
    }


}