package com.example.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class bill extends AppCompatActivity implements PaymentResultListener {
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
                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_sNummj8smufOdn");
                checkout.setImage(com.razorpay.R.drawable.rzp_logo);
                JSONObject object =new JSONObject();
                try {
                    object.put("name","Android coding");
                    object.put("Description","Test Paymnet");
                    object.put("theme.color","0093DD");
                    object.put("Currency","INR");
                    object.put("amount",amount);
                    checkout.open(bill.this,object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}