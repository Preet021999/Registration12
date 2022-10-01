package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class customer_home extends AppCompatActivity {
    Button btnop,btnest,btnlogout,btnbill,btnfeed;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        btnop=(Button)findViewById(R.id.btnop);
        btnest=(Button)findViewById(R.id.btnest);
        btnlogout=(Button)findViewById(R.id.btnlogout);
        btnbill=(Button)findViewById(R.id.btnbill);
        btnfeed=(Button)findViewById(R.id.btnfeed);
        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

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
                editor.putString("isLogin","false");
                editor.commit();
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