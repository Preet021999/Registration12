package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class driver_home extends AppCompatActivity {

    TextView dest, height, width, del_price, name;
    Button btnLogout, accept, reject;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    FirebaseFirestore db;
    private ArrayList<String> destination = new ArrayList<String>();
    private ArrayList<String> obj_height = new ArrayList<String>();
    private ArrayList<String> obj_width = new ArrayList<String>();
    private ArrayList<String> obj_price = new ArrayList<String>();
    private ArrayList<String> obj_name = new ArrayList<String>();
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);
        dest = (TextView) findViewById(R.id.dest);
        height = (TextView) findViewById(R.id.height);
        width = (TextView) findViewById(R.id.width);
        del_price = (TextView) findViewById(R.id.del_price);
        name = (TextView) findViewById(R.id.name);
        db = FirebaseFirestore.getInstance();
        getorderDocs();


        sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString("isLogin", "false").equals("false")) {
            openLogin();
        }


        btnLogout = findViewById(R.id.BtnDriver_logout);

        accept = findViewById(R.id.accept);
        reject = findViewById(R.id.reject);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("isLogin", "false");
                editor.commit();
                openLogin();


            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dest.setVisibility(View.GONE);
                height.setVisibility(View.GONE);
                width.setVisibility(View.GONE);
                del_price.setVisibility(View.GONE);
                name.setVisibility(View.GONE);
                accept.setVisibility(View.GONE);
                reject.setVisibility(View.GONE);
            }
        });
    }

    //protected void onStart() {
//
//    super.onStart();
//
//}
    private void openLogin() {
        startActivity(new Intent(driver_home.this, LoginActivity.class));

    }

    public void getorderDocs() {
        db.collection("order")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                destination.add(document.getString("Destination"));
                                obj_height.add(document.getString("Height"));
                                obj_width.add(document.getString("Width"));
                                obj_price.add(document.getString("Price"));
                                obj_name.add(document.getString("Name"));
                                System.out.println(destination);
                                System.out.println(obj_height);
                                System.out.println(obj_width);
                                System.out.println(obj_price);
                                System.out.println(obj_name);
                                dest.setText("Destination: " + destination.get(0));
                                height.setText("Height: " + obj_height.get(0));
                                name.setText("Name: " + obj_name.get(0));
                                del_price.setText("Price: " + obj_price.get(0));
                                width.setText("Width: " + obj_width.get(0));

                                accept.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(driver_home.this, bid.class);
                                        intent.putExtra("name", obj_name.get(0));
                                        intent.putExtra("price", obj_price.get(0));
                                        startActivity(intent);
                                    }
                                });

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

    @Override
    protected void onResume() {
        super.onResume();
        dest.setVisibility(View.GONE);
        height.setVisibility(View.GONE);
        width.setVisibility(View.GONE);
        del_price.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        accept.setVisibility(View.GONE);
        reject.setVisibility(View.GONE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}