package com.example.hackapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button; //manual import
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {

    EditText u_input, p_input;
    Button loginBtn;
    Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        u_input = findViewById(R.id.username_input);
        p_input = findViewById(R.id.password_input);
        loginBtn = findViewById(R.id.login_btn);
        signUp = findViewById(R.id.logSignUp);

        loginBtn.setOnClickListener(new View.OnClickListener() {


            //username and password input

            @Override
            public void onClick(View v) {

                String username = u_input.getText().toString();
                String password = p_input.getText().toString();
                //login credential
                Log.i("Login Credential",
                        "Username:" + username +
                                "\nPassword: " + password);


                //setContentView(R.layout.activity_profile);
                Intent intent = new Intent(MainActivity.this,Home.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {

            //username and password input

            @Override
            public void onClick(View v) {

                //setContentView(R.layout.activity_profile);
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //adding data


}