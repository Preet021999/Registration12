package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private CheckBox checkBox, checkBox2;


    EditText UserName, PhoneNumber, Address, Email, Password, C_Password;

    FirebaseFirestore db;
    TextView back;
    Button btn_driver;
    Button btn_reg;
    AlertDialog.Builder builder;

    String uname, phone, address, email, pass, c_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //REGISTER - ALREADY HAVE ACC - DRIVER REGISTRATION  CLICKS

        UserName = findViewById(R.id.inputUsername);
        PhoneNumber = findViewById(R.id.inputPhone);
        Address = findViewById(R.id.inputAddress);
        Email = findViewById(R.id.inputEmail);

        db = FirebaseFirestore.getInstance();

        Password = findViewById(R.id.inputPassword);
        C_Password = findViewById(R.id.inputConformPassword);

        back = findViewById(R.id.alreadyHaveAccount);
        btn_driver = findViewById(R.id.r_driver);
        btn_reg = findViewById(R.id.c_btnRegister);

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btn_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, RegisterActivity2.class));
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation();
            }
        });


        //CHECKBOX TERMS and CONDITION

        checkBox = findViewById(R.id.terms1);


        builder = new AlertDialog.Builder(this);

        btn_reg.setEnabled(false);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    builder.setTitle("Terms And Conditions");
                    builder.setMessage("Each registration is for a single user only. On registration, you will choose a user name and password (\"ID\"). You are not allowed to share your ID or give access to your account to anyone else. \n" +
                            "\n" +
                            "These terms and conditions constitute the entire agreement between you and Shipping Wars for your use of the Shipping Wars Application and services from Shipping Wars. They supersede all previous communications, representations and arrangements, either written or oral.\n" +
                            "\n" +
                            "All Content created and published on the digital platforms under the mobile application ,its licensors who own all intellectual property rights (including copyright and database rights) No intellectual property rights in any of the content are transferred to you while you consume the content on this platform. \"SW\" is registered trade mark of Shipping Wars and you may not use them without prior written permission from Shipping wars. You are permitted to use the content on this platform only as set out in our Copyright Policy.\n");

                    builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            btn_reg.setEnabled(true);
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            checkBox.setChecked(false);
                        }
                    });
                    builder.show();
                } else {
                    btn_reg.setEnabled(false);
                }
            }
        });

        //Password Show
        checkBox2 = findViewById(R.id.Spass);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Password.setTransformationMethod(null);
                    C_Password.setTransformationMethod(null);
                } else {
                    Password.setTransformationMethod(new PasswordTransformationMethod());
                    C_Password.setTransformationMethod(new PasswordTransformationMethod());
                }

            }
        });


    }


    private void validation() {
        /** Database connection **/
        String uname = UserName.getText().toString();
        String phone = PhoneNumber.getText().toString();
        String address = Address.getText().toString();
        String email = Email.getText().toString();
        String pass = Password.getText().toString();
        String c_pass = C_Password.getText().toString();

        if (uname.isEmpty()) {
            UserName.setError("Please Fill this Filed");
            UserName.requestFocus();
        }
        if (phone.isEmpty()) {
            PhoneNumber.setError("Please Fill this Filed");
            PhoneNumber.requestFocus();
        }
        if (address.isEmpty()) {
            Address.setError("Please Fill this Filed");
            Address.requestFocus();
        }
        if (email.isEmpty()) {
            Email.setError("Please Fill this Filed");
            Email.requestFocus();
        }
        if (pass.isEmpty()) {
            Password.setError("Please Fill this Filed");
            Password.requestFocus();
        }
        if (c_pass.isEmpty()) {
            C_Password.setError("Please Fill this Filed");
            C_Password.requestFocus();
        } else {
            Map<String, Object> user = new HashMap<>();
            user.put("Name", uname);
            user.put("Phone Number", phone);
            user.put("Address", address);
            user.put("Email", email);
            user.put("Password", pass);
//            user.put("Confirm Password",c_pass);
            db.collection("user")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(RegisterActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
    }
}
