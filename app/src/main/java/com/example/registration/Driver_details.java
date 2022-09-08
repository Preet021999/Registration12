package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Driver_details extends AppCompatActivity {
    String uname,phone,email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);
        Intent intent=getIntent();
        uname=intent.getStringExtra("uname");
        phone=intent.getStringExtra("phone");
        email=intent.getStringExtra("email");
        pass=intent.getStringExtra("pass");
    }
}