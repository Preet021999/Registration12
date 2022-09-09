package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class customer_home extends AppCompatActivity {
    Button btnop,btnest,btnlogout,btnbill,btnfeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        btnest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(customer_home.this,estimatecost.class));
                finish();
            }
        });
        btnop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(customer_home.this,order_page.class));
                finish();
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(customer_home.this,LoginActivity.class));
                finish();
            }
        });
        btnbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(customer_home.this,bill.class));
                finish();
            }
        });
        btnfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(customer_home.this,feedback.class));
                finish();
            }
        });
    }
}