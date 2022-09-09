package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class feedback extends AppCompatActivity {

    EditText et_source,edmulti;
    RatingBar et_ratingbar;
    Button btnsbf;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        et_source = findViewById(R.id.et_source);
        edmulti = findViewById(R.id.edmulti);
        et_ratingbar =findViewById(R.id.et_ratingBar);
        btnsbf = findViewById(R.id.btnsbf);
        db = FirebaseFirestore.getInstance();

        btnsbf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbacksub();
            }
        });
    }

    private void feedbacksub() {
        String name = et_source.getText().toString();
        String feedback = edmulti.getText().toString();
        float ratingbar = et_ratingbar.getRating();
        Map<String,Object> FEEDBACK = new HashMap<>();
        FEEDBACK.put("Name",name);
        FEEDBACK.put("Feedback",feedback);
        FEEDBACK.put("Rating Bar",ratingbar);
        db.collection("FEEDBACK")
                .add(FEEDBACK)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(feedback.this, "Feedback Submit Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(feedback.this,customer_home.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(feedback.this, "Failed", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(feedback.this,customer_home.class));
                    }
                });

    }
}