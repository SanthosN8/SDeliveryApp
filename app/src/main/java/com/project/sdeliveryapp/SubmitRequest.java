package com.project.sdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubmitRequest extends AppCompatActivity {
     Button rbt,cbt;
     DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_request);

        rbt = findViewById(R.id.rbt);
        cbt = findViewById(R.id.cbt);
        db = new DatabaseHelper(this);
        String latitude = CurrentLocation.loclat;
        String longtitude = CurrentLocation.loclon;
        String sname = ServiceSelection.servename;
        String radio = ServiceSelection.selectradio;
        String service = Service_nav.requireditem;
        String phone = Service_nav.telephone;

        String dlongti = DeliveryLocationPick.dlongti;
        String dlat = DeliveryLocationPick.dlat;

        rbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choose = SelectLocation.choose;
                if (choose == "current") {
                    boolean isInserted = db.insertservicetable(sname, radio, service, phone, latitude, longtitude);
                    if (isInserted == true)
                        Toast.makeText(SubmitRequest.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SubmitRequest.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

                }else{
                    boolean isInserted = db.insertservicetable(sname, radio, service, phone, dlat, dlongti);
                    if (isInserted == true)
                        Toast.makeText(SubmitRequest.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SubmitRequest.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

                }
                showvalue();
            }

        });

        cbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboard();
            }
        });
    }

    public void opendashboard(){
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

    public void showvalue(){
        Intent intent = new Intent(this, DeliveryEndActivity.class);
        startActivity(intent);
    }
}