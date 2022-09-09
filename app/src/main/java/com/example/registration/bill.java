package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class bill extends AppCompatActivity implements PaymentResultListener {
    Button billpay;
    TextView txtprice,txtname,txtpname,txtsource,txtdest,txtdname,txtdnum,txtemail;
   private int fAmount;
    String sAmount;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseFirestore db;
    ArrayList<String> cus_name=new ArrayList<String>();
    ArrayList<String> dri_name=new ArrayList<String>();
    ArrayList<String> pro_name = new ArrayList<String>();
    ArrayList<String> source = new ArrayList<String>();
    ArrayList<String> dest = new ArrayList<String>();
    ArrayList<String> dri_num = new ArrayList<String>();
    ArrayList<String> dri_email = new ArrayList<String>();
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        getuserDocs();
        getdriverDocs();
        getorderDocs();
        db = FirebaseFirestore.getInstance();
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("bid");
        billpay = findViewById(R.id.btpay);
        txtprice=findViewById(R.id.txtprice);
        txtname=findViewById(R.id.txtname);

//        String sAmount = "100000";
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                sAmount = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + sAmount);
                txtprice.setText(sAmount);
                fAmount=Integer.parseInt(txtprice.getText().toString())*100;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        System.out.println(fAmount);


//        int amount = Integer.parseInt(sAmount);
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
                    object.put("amount",fAmount);
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
    public void getuserDocs(){
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                cus_name.add(document.getString("Name"));
                                source.add(document.getString("Address"));
                                txtsource.setText(source.get(0));
                                //cus_pass.add(document.getString("Password"));
//                                System.out.println(cus_email);
//                                System.out.println(cus_pass);
//                         for (int i=0;i<cus_email.size();i++){
//                             System.out.println(cus_email.get(i));
//                         }
                            txtname.setText(cus_name.get(0));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
    public void getdriverDocs() {
        db.collection("driver")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                dri_name.add(document.getString("Name"));
                                dri_num.add(document.getString("Phone Number"));
                                dri_email.add(document.getString("Email"));
//                                dri_pass.add(document.getString("Password"));
//                                System.out.println(dri_name);
//                                System.out.println(dri_pass);
//                                for (int i=0;i<cus_email.size();i++){
//                                    System.out.println(cus_email.get(i));
//                                }
                                txtdname.setText(dri_name.get(0));
                                txtdnum.setText(dri_num.get(0));
                                txtemail.setText(dri_email.get(0));

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
        public void getorderDocs(){
            db.collection("order")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    pro_name.add(document.getString("Name"));
                                    dest.add(document.getString("Destination"));
                                    txtpname.setText(pro_name.get(0));
                                    txtdest.setText(dest.get(0));
//                                dri_pass.add(document.getString("Password"));
//                                System.out.println(dri_name);
//                                System.out.println(dri_pass);
//                                for (int i=0;i<cus_email.size();i++){
//                                    System.out.println(cus_email.get(i));
//                                }

                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

    }
}