package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAllOrders extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    ArrayList<OrderModel> orderModels = new ArrayList<OrderModel>();
    TextView orders;

    String orderString = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_orders);



        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("orders");
        orders = findViewById(R.id.orders);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    OrderModel orderModel;

                    orderModel = dataSnapshot1.getValue(OrderModel.class);

                    if(!orderModel.getCurrentStatus().matches("Delivered")){
                        orderModels.add(orderModel);
                        orderString += dataSnapshot1.getKey()+"\n"+orderModel.getFoodname()+"\n"+"Qty: "+orderModel.getQty()+
                                "\nPrice: "+orderModel.getPrice()+"\nTotal Price: "+orderModel.getTotalprice()+
                                "\nCurrent Status: "+orderModel.getCurrentStatus()+"\n\n\n";
                    }

                }
                orders.setText(orderString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}