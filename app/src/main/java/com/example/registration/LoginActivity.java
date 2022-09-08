package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText uname,pass;

    Button btnLogin;
    CheckBox checkBox;

    String id,password;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("isLogin","false").equals("true")){
            openDash();
        }

        TextView rPage=findViewById(R.id.textViewSignUp);
         uname=findViewById(R.id.inputEmail);
         pass=findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.btnlogin);
        checkBox=findViewById(R.id.Spass);
        //------------------v


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

    }

    private void validateData() {
        id = uname.getText().toString();
        password = pass.getText().toString();

        if(id.isEmpty()){
            uname.setError("Please Fill this Filed");
            uname.requestFocus();
        }
        if(password.isEmpty()){
            pass.setError("Please Fill this Filed");
            pass.requestFocus();
        }
        else if(id.equals("abc") && password.equals("abc")){
            editor.putString("isLogin","true");
            editor.commit();
            openDash();
        }
        else {
            Toast.makeText(this,"Id & PassWord is Incorrect",Toast.LENGTH_SHORT);
        }
    }

    private void openDash() {

        startActivity(new Intent(LoginActivity.this,driver_home.class));
        finish();

    }


}
