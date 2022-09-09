package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class bid extends AppCompatActivity {

    TextView C_bid,pro_name;
    EditText Amount;
    Button Exit,E_Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);

        C_bid = findViewById(R.id.C_bid);
        pro_name = findViewById(R.id.pro_name);
        Amount = findViewById(R.id.Amount);
        Exit = findViewById(R.id.exit);
        E_Amount = findViewById(R.id.Enter_amount);
        Intent intent=getIntent();
        String name= intent.getStringExtra("name");
        String price= intent.getStringExtra("price");
        pro_name.setText(name);
        C_bid.setText(price+"  ₹");
        FirebaseDatabase database = FirebaseDatabase.getInstance();


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
                DatabaseReference myRef = database.getReference("bid");

                myRef.setValue(amount);
            }
        });
    }
}