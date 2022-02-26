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
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    EditText email;
    EditText password;
    Button register;
    Button back;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        register = findViewById(R.id.btnRegister);
        back = findViewById(R.id.btnBackReg);
        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>()
                  {
                      @Override
                      public void onSuccess(AuthResult authResult) {
                          FirebaseUser user = firebaseAuth.getCurrentUser();
                          Toast.makeText(Register.this, "Registration completed successfully!",
                                  Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(Register.this, Login.class));
                      }

                  }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Registration failed!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            });
back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Register.this, Login.class));
                }
        });
    }
}

