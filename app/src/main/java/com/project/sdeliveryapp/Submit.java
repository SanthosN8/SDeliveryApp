package com.project.sdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Submit extends AppCompatActivity {
     Button submitbt,cancelbt;
     DataBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        submitbt = findViewById(R.id.submitbt);
        cancelbt = findViewById(R.id.cancelbt);
        db = new DataBHelper(this);


        String pname = dashboard_nav.uname;
        String goods = dashboard_nav.items;
        String phone = dashboard_nav.phoneno;
        String latitude = CurrentLocation.loclat;
        String longtitude = CurrentLocation.loclon;

        String dlongti = DeliveryLocationPick.dlongti;
        String dlat = DeliveryLocationPick.dlat;


        cancelbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboard();
            }
        });

        submitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choose = SelectLocation.choose;
                if(choose == "current") {
                    boolean isInserted = db.insertdatatable(pname, goods, phone, latitude, longtitude);
                    if (isInserted == true)
                        Toast.makeText(Submit.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Submit.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isInserted = db.insertdatatable(pname, goods, phone, dlat, dlongti);
                    if (isInserted == true)
                        Toast.makeText(Submit.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Submit.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
                showvalue();
            }
        });
    }


    public void opendashboard(){
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

    public void showvalue(){
        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }


}