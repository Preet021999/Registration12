package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class bid extends AppCompatActivity {

    TextView C_bid;
    EditText Amount;
    Button Exit,E_Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);

        C_bid = findViewById(R.id.C_bid);
        Amount = findViewById(R.id.Amount);
        Exit = findViewById(R.id.exit);
        E_Amount = findViewById(R.id.Enter_amount);
        Intent intent=getIntent();
        String name= intent.getStringExtra("name");
        String price= intent.getStringExtra("price");
        C_bid.setText(price+"  ₹");

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bid.this,driver_home.class));
            }

        });
        E_Amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount=Amount.getText().toString();
                C_bid.setText(amount+"  ₹");

            }
        });
    }
}