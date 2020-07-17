package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFoodDetails extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference myRef;

    EditText foodName, foodDesc, foodPrice;
    Button addFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_details);

        db = db.getInstance();
        myRef = db.getReference("foods");

        foodName = findViewById(R.id.foodName);
        foodDesc = findViewById(R.id.foodDesc);
        foodPrice = findViewById(R.id.foodPrice);

        addFood = findViewById(R.id.addfood);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = foodName.getText().toString();
                String fdesc = foodDesc.getText().toString();
                String fprice = foodPrice.getText().toString();

                FoodDetails f = new FoodDetails(fname, fdesc, fprice);
                myRef.child(fname).setValue(f).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Food Added Successfully!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), FoodMenu.class);
                        startActivity(i);
                    }
                });
            }
        });

    }
}