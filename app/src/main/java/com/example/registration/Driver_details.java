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

public class Driver_details extends AppCompatActivity {
    String uname,phone,email,pass;
    EditText vname,vno,licNo,rcBook,aadharNo,insurance;
    Button submit;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);
        vname=(EditText)findViewById(R.id.editText);
        vno=(EditText)findViewById(R.id.editText2);
        licNo=(EditText)findViewById(R.id.editText3);
        rcBook=(EditText)findViewById(R.id.editText4);
        aadharNo=(EditText)findViewById(R.id.AddharNo2);
        insurance=(EditText)findViewById(R.id.Insurance2);
        submit=(Button)findViewById(R.id.submit);
        db = FirebaseFirestore.getInstance();

        Intent intent=getIntent();
        uname=intent.getStringExtra("uname");
        phone=intent.getStringExtra("phone");
        email=intent.getStringExtra("email");
        pass=intent.getStringExtra("pass");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation();
            }
        });
    }
    private void validation() {
        /** Database connection **/
        String veh_name = vname.getText().toString();
        String veh_no = vno.getText().toString();
        String lic_no = licNo.getText().toString();
        String rc_book = rcBook.getText().toString();
        String aadhar_no = aadharNo.getText().toString();
        String insurance_no= insurance.getText().toString();

        if (veh_name.isEmpty()){
            vname.setError("Please Fill this Filed");
            vname.requestFocus();
        }
        if (veh_no.isEmpty()){
            vno.setError("Please Fill this Filed");
            vno.requestFocus();
        }
        if (lic_no.isEmpty()){
            licNo.setError("Please Fill this Filed");
            licNo.requestFocus();
        }
        if (rc_book.isEmpty()){
            rcBook.setError("Please Fill this Filed");
            rcBook.requestFocus();
        }
        if (aadhar_no.isEmpty()){
            aadharNo.setError("Please Fill this Filed");
            aadharNo.requestFocus();
        }
        if (insurance_no.isEmpty()){
            insurance.setError("Please Fill this Filed");
            insurance.requestFocus();
        }
        else {
            Map<String,Object> driver = new HashMap<>();
            driver.put("Name",uname);
            driver.put("Phone Number",phone);
//            user.put("Address",address);
            driver.put("Email",email);
            driver.put("Password",pass);
            driver.put("Vehicle_Name",veh_name);
            driver.put("Vehicle_No",veh_no);
            driver.put("License_No",lic_no);
            driver.put("Aadhar_No",aadhar_no);
            driver.put("RC_Book",rc_book);
            driver.put("Insurance",insurance_no);
            // user.put("Confirm Password",c_pass);
            db.collection("driver")
                    .add(driver)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Driver_details.this, "Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Driver_details.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            startActivity(new Intent(Driver_details.this,test.class));
        }
    }
}