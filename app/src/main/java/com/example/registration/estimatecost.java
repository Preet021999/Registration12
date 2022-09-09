package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class estimatecost extends AppCompatActivity {
    EditText edtkm,edtrps;
    TextView txtdis;
    Button btnprice,btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimatecost);
        edtkm = findViewById(R.id.edtkm);
        edtrps = findViewById(R.id.edtrps);
        txtdis = findViewById(R.id.txtdis);
        btnprice = findViewById(R.id.btnprice);
        btnback =findViewById(R.id.btnback);

        btnprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtkm.getText().toString().length()==0){
                    edtkm.setText("0");
                }
                if(edtrps.getText().toString().length()==0){
                    edtrps.setText("0");
                }
                Float v1 = Float.valueOf(edtkm.getText().toString());
                Float v2 = Float.valueOf(edtrps.getText().toString());

                Float btnprice = v1*v2;
                txtdis.setText(String.valueOf(btnprice));
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(estimatecost.this,customer_home.class));
            }
        });
    }
}