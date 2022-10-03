package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Handler h = new Handler();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String email, password;
    ArrayList<String> cus_email = new ArrayList<String>();
    ArrayList<String> cus_pass = new ArrayList<String>();
    ArrayList<String> dri_email = new ArrayList<String>();
    ArrayList<String> dri_pass = new ArrayList<String>();
    FirebaseFirestore db;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        getuserDocs();
        getdriverDocs();

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        email = sharedPreferences.getString("email", "");
        password = sharedPreferences.getString("pass", "");
        System.out.println("Email "+email);
        System.out.println("Pass "+password);

//        if (sharedPreferences.getString("isLogin", "false").equals("false")) {
//            openlogin();
//        }
        if (sharedPreferences.getString("isLogin", "false").equals("true")) {
            for (int i = 0; i < cus_email.size(); i++) {
                if (email.equals(cus_email.get(i)) && password.equals(cus_pass.get(i))) {
                    openDash();
                } else {
//                    Toasty.error(LoginActivity.this,"Incorrect login details",Toast.LENGTH_SHORT,true).show();
                }


            }
            for (int i = 0; i < dri_email.size(); i++) {
                if (email.equals(dri_email.get(i)) && password.equals(dri_pass.get(i))) {
                    openDash1();
                } else {
//                    Toasty.error(LoginActivity.this,"Incorrect login details",Toast.LENGTH_SHORT,true).show();
                }

            }

        }
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }



    private void openDash() {

        startActivity(new Intent(MainActivity.this, customer_home.class));
        finish();

    }

    private void openDash1() {

        startActivity(new Intent(MainActivity.this, driver_home.class));
        finish();

    }

    private void openlogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void getuserDocs() {
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                cus_email.add(document.getString("Email"));
                                cus_pass.add(document.getString("Password"));
                                System.out.println(cus_email);
                                System.out.println(cus_pass);
//                         for (int i=0;i<cus_email.size();i++){
//                             System.out.println(cus_email.get(i));
//                         }

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
                                dri_email.add(document.getString("Email"));
                                dri_pass.add(document.getString("Password"));
                                System.out.println(dri_email);
                                System.out.println(dri_pass);
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
