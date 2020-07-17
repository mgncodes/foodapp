package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpAdmin extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText name, email, password, cpassword;
    TextView signinnow;
    Button loginnow;
    FirebaseDatabase db;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_admin);

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        loginnow = findViewById(R.id.signup);
        signinnow = findViewById(R.id.login2);

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("Admins");

        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameip = name.getText().toString();
                String emailip = email.getText().toString();
                String passwordip = password.getText().toString();
                String cpasswordip = cpassword.getText().toString();

                if(emailip.equals("")) {
                    Toast.makeText(getApplicationContext(),"Please Enter your Email ID", Toast.LENGTH_LONG).show();
                }
                else if(nameip.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Name", Toast.LENGTH_LONG).show();
                }
                else if(passwordip.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_LONG).show();
                }
                else if(cpasswordip.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Password Again", Toast.LENGTH_LONG).show();
                }
                else if(passwordip.length() < 7) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Password (Min. 7 characters)", Toast.LENGTH_LONG).show();
                }
                else if(!cpasswordip.equals(passwordip)) {
                    Toast.makeText(getApplicationContext(),"Password Not Matched", Toast.LENGTH_LONG).show();
                }
                else {
                    signUp(emailip,passwordip,nameip);
                }
            }
        });
        signinnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpAdmin.this, MainActivity.class));
                finish();
            }
        });


    }
    void signUp(String emailip, String passwordip, final String nameip) {
        mAuth.createUserWithEmailAndPassword(emailip, passwordip).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    myRef.child(mAuth.getUid()).child("name").setValue(nameip).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(SignUpAdmin.this, "Account Created Successfully!", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    // If sign in fails, display a message to the user
                    Toast.makeText(SignUpAdmin.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}