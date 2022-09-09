package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class bill extends AppCompatActivity {
    Button billpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        billpay = findViewById(R.id.btpay);
        String sAmount = "100000";

        int amount = Math.round(Float.parseFloat(sAmount) * 100);
        billpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}