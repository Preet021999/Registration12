package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    EditText uname,pass;

    Button btnLogin;
    CheckBox checkBox;
    TextView r2page,rPage;

    String email,password;
     ArrayList<String> cus_email=new ArrayList<String>();
     ArrayList<String> cus_pass=new ArrayList<String>();
    ArrayList<String> dri_email= new ArrayList<String>();
    ArrayList<String> dri_pass= new ArrayList<String>();
    FirebaseFirestore db;
    private static final String TAG = "MainActivity";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = FirebaseFirestore.getInstance();
        getuserDocs();
        getdriverDocs();
        System.out.println(cus_email);
        System.out.println(cus_pass);
        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        if(sharedPreferences.getString("isLogin","false").equals("true")){
//            openDash();
//        }

        rPage=findViewById(R.id.textViewSignUp);
        r2page = findViewById(R.id.txtsignupdriver);
         uname=findViewById(R.id.inputEmail);
         pass=findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.btnlogin);
        checkBox=findViewById(R.id.Spass);
        //------------------v

        System.out.println(cus_email);
        System.out.println(cus_pass);
       //------------------show password-----------
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
             if(b)
             {
                 pass.setTransformationMethod(null);
             }else{
                 pass.setTransformationMethod(new PasswordTransformationMethod());
             }

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                validateData();
            }
        });

        rPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        r2page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity2.class));
            }
        });

    }

    private void validateData() {
        email = uname.getText().toString();
        password = pass.getText().toString();
        System.out.println(email);
        System.out.println(password);

        if(email.isEmpty()){
            uname.setError("Please Fill this Filed");
            uname.requestFocus();
        }
        else if(password.isEmpty()){
            pass.setError("Please Fill this Filed");
            pass.requestFocus();
        }
        else {
            for (int i=0;i<cus_email.size();i++)
            {
                if(email.equals(cus_email.get(i)) && password.equals(cus_pass.get(i))){
                    editor.putString("isLogin","true");
                    editor.commit();
                    openDash();
                }
                else {
//                    Toasty.error(LoginActivity.this,"Incorrect login details",Toast.LENGTH_SHORT,true).show();
                }


            }
            for (int i=0;i<dri_email.size();i++)
            {
                if(email.equals(dri_email.get(i)) && password.equals(dri_pass.get(i))){
                    editor.putString("isLogin","true");
                    editor.commit();
                    openDash1();
                }
                else{
//                    Toasty.error(LoginActivity.this,"Incorrect login details",Toast.LENGTH_SHORT,true).show();
                }

            }

        }

    }

    private void openDash() {

        startActivity(new Intent(LoginActivity.this,customer_home.class));
        finish();

    }
    private void openDash1() {

        startActivity(new Intent(LoginActivity.this,driver_home.class));
        finish();

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
    public void getdriverDocs(){
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
