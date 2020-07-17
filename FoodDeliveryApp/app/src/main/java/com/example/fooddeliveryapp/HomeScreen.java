package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {

    Button signout, gotomenu, addfood,viewOrders,setStatus;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mAuth = FirebaseAuth.getInstance();

        signout = findViewById(R.id.signout);
        gotomenu = findViewById(R.id.gotofoodmenu);
        addfood = findViewById(R.id.addfood);
        viewOrders = findViewById(R.id.viewOrders);
        setStatus = findViewById(R.id.setStatus);

        setStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SetOrderStatus.class);
                startActivity(intent);
            }
        });

        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ViewAllOrders.class);
                startActivity(intent);
            }
        });



        gotomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(HomeScreen.this, FoodMenu.class);
                startActivity(i2);
            }
        });

        addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(), AddFoodDetails.class);
                startActivity(i3);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i1 = new Intent(HomeScreen.this, MainActivity.class);
                startActivity(i1);
                finish();
            }
        });



    }
}