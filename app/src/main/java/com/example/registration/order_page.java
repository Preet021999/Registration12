package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class order_page extends AppCompatActivity {
    EditText edtname,dest,price,height,width;
    Button btnsub,btnback;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        edtname = findViewById(R.id.edtname);
        dest = findViewById(R.id.dest);
        price =findViewById(R.id.price);
        height = findViewById(R.id.height);
        width = findViewById(R.id.width);
        btnsub = findViewById(R.id.btnsub);
        btnback = findViewById(R.id.btnback);
        db = FirebaseFirestore.getInstance();
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(order_page.this,customer_home.class));
            }
        });
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datastore();
            }
        });
    }

    private void datastore() {

        String name = edtname.getText().toString();
        String destination = dest.getText().toString();
        String edtprice = price.getText().toString();
        String edtheight = height.getText().toString();
        String edtwidth = width.getText().toString();
        if (name.isEmpty()){
            edtname.setError("Please Fill this Filed");
            edtname.requestFocus();
        }
        if (destination.isEmpty()){
            dest.setError("Please fill the field");
            dest.requestFocus();
        }
        if (edtprice.isEmpty()){
            price.setError("Please Fill this Filed");
            price.requestFocus();
        }
        if (edtheight.isEmpty()){
            height.setError("Please Fill this Filed");
            height.requestFocus();
        }
        if (edtwidth.isEmpty()){
            width.setError("Please Fill this Filed");
            width.requestFocus();
        }
        else{
            Map<String,Object> order = new HashMap<>();
            order.put("Name",name);
            order.put("Destination",destination);
            order.put("Price",edtprice);
            order.put("Height",edtheight);
            order.put("Width",edtwidth);
            db.collection("order")
                    .add(order)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(order_page.this, "Order Place Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(order_page.this,customer_home.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(order_page.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }
}