package com.project.sdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeliveryEndActivity extends AppCompatActivity {
     DatabaseHelper ddb;
     Button viewdelivery, closedelivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_end);
        ddb = new DatabaseHelper(this);

        viewdelivery = findViewById(R.id.viewdorder);
        closedelivery = findViewById(R.id.closedview);

        closedelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboard();
            }
        });

        viewdelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = ddb.getLastTableRow();

                if(cursor != null && cursor.moveToFirst()){
                    @SuppressLint("Range") String name1 = cursor.getString(cursor.getColumnIndex("name"));
                    @SuppressLint("Range") String type1 = cursor.getString(cursor.getColumnIndex("type"));
                    @SuppressLint("Range") String problem1 = cursor.getString(cursor.getColumnIndex("problem"));
                    @SuppressLint("Range") String phone1 = cursor.getString(cursor.getColumnIndex("phone"));
                    @SuppressLint("Range") String latitude1 = cursor.getString(cursor.getColumnIndex("latitude"));
                    @SuppressLint("Range") String longtitude1 = cursor.getString(cursor.getColumnIndex("longtitude"));

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(DeliveryEndActivity.this);
                    builder1.setTitle("Request Details");
                    builder1.setMessage("Name " + name1 + "\nType: " + type1 + "\nProblem: " + problem1 + "\nPhone: " + phone1 + "\nLatitude: " + latitude1 + "\nLongtitude: " + longtitude1);
                    builder1.setPositiveButton("OK", null);
                    builder1.show();

                }
                if(cursor != null){
                    cursor.close();
                }
                ddb.close();
            }
        });


    }
    public void opendashboard(){
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }
}