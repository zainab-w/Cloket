package com.example.cloket;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addCategory extends AppCompatActivity {

    Button btnSubmitCateg, cancelbtn;
    EditText categorynameET,categorylimitET;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        cancelbtn = findViewById(R.id.cancelbtn);
        btnSubmitCateg = findViewById(R.id.btnSubmitCategory);
        categorylimitET = findViewById(R.id.categoryLimitET);
        categorynameET = findViewById(R.id.categoryNameET);
        firebaseAuth = FirebaseAuth.getInstance();

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();
            }
        });

        btnSubmitCateg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategoryToFirebase();
            }
        });
    }

    private void backButton()
    {
        startActivity(new Intent(addCategory.this, HomePage.class));
    }

    String categoryName;
    String categorylimit;
    private void valiateData()
    {
        categoryName = categorynameET.getText().toString().trim();
        categorylimit = categorylimitET.getText().toString().trim();
        if(TextUtils.isEmpty(categoryName))
        {
            Toast.makeText(this, "Enter category name"
                    ,Toast.LENGTH_SHORT).show();
        }
        else
        {
            addCategoryToFirebase();
        }
    }

    private void addCategoryToFirebase()
    {
        long timestamp = System.currentTimeMillis();
        categoryName = categorynameET.getText().toString().trim();
        categorylimit = categorylimitET.getText().toString().trim();
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("id","" + timestamp);
        hashMap.put("category","" + categoryName);
        hashMap.put("goal","" + categorylimit);

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("Categories");

       // saves data into firebase
        ref.child(""+categoryName).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(addCategory.this,
                                "Category added successfully!"
                                ,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(addCategory.this, HomePage.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addCategory.this,
                                "Category added unsuccessfully!"
                                ,Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
