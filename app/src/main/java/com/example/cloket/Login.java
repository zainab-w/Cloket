package com.example.cloket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login;
    Button back;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        login = findViewById(R.id.btnLogin);
        back = findViewById(R.id.btnBack);
        firebaseAuth = FirebaseAuth.getInstance();




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(getEmail,getPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Login.this, "Welcome User", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,HomePage.class));
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }


        });
        back.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(Login.this,Register.class));
        }
        });
        }
    };

