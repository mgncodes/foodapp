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
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FixOrderStatus extends AppCompatActivity {

    String orderNumber;
    OrderModel orderModel;

    TextView orderDetails;
    EditText status;
    Button submit;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_order_status);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("orders");

        Intent intent = getIntent();
        orderNumber = intent.getStringExtra("orderNo");
        orderModel = (OrderModel) intent.getSerializableExtra("orderModel");


        submit = findViewById(R.id.submit);
        orderDetails = findViewById(R.id.orderDetails);
        status = findViewById(R.id.status);

        orderDetails.setText(orderModel.getOrderno()+"\n"+orderModel.getFoodname()+"\n"+"Qty: "+orderModel.getQty()+
                "\nPrice: "+orderModel.getPrice()+"\nTotal Price: "+orderModel.getTotalprice()+"\nCurrent Status: "+orderModel.getCurrentStatus()+"\n\n\n");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String statusInput = status.getText().toString();
                if(statusInput.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please set a Status to update!",Toast.LENGTH_LONG).show();
                }
                else{
                    myRef.child(orderNumber).child("currentStatus").setValue(statusInput).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Status updated Successfully!",Toast.LENGTH_LONG).show();
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}