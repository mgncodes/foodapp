package com.example.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    TextView signupnow;
    Button Login;

    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Intent i = new Intent(MainActivity.this, HomeScreen.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.signup);
        signupnow = findViewById(R.id.signup2);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailip = email.getText().toString();
                String passwordip = password.getText().toString();

                if(emailip.equals("")) {
                    Toast.makeText(MainActivity.this,"Please Enter your Email ID", Toast.LENGTH_LONG).show();
                }
                else if(passwordip.equals("")) {
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_LONG).show();
                }
                else if(passwordip.length() < 7) {
                    Toast.makeText(MainActivity.this, "Please Enter Valid Password (Min. 7 characters)", Toast.LENGTH_LONG).show();
                }
                else {
                    signIn(emailip, passwordip);
                }
            }
        });

        signupnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUpAdmin.class);
                startActivity(i);
                //startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                finish();
            }
        });

    }
    void signIn(String emailip, String passwordip) {
        mAuth.signInWithEmailAndPassword(emailip, passwordip).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent i = new Intent(getApplicationContext(),HomeScreen.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}