package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodMenu extends AppCompatActivity {

    Button add;
    TextView textView;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutMgr;

    FirebaseDatabase db;
    DatabaseReference myRef;

    TextView foodList;
    String foods = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        foodList = findViewById(R.id.foodList);

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("foods");

        textView = findViewById(R.id.textView);
        final ArrayList<FoodDetails> FoodList = new ArrayList<FoodDetails>();

        myRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                   FoodDetails f;
                   Log.d("TAG", dataSnapshot1.toString());
                   f = dataSnapshot1.getValue(FoodDetails.class);
                   FoodList.add(f);

                   foods += f.getFoodname()+"\n"+f.getDescription()+"\n\n"+"Rs. "+f.getPrice()+"\n\n\n\n";
               }
               foodList.setText(foods);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }
}