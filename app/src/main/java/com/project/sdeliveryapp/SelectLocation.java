package com.project.sdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLocation extends AppCompatActivity {
    Button currentlocation, deliverylocation;
    public static String choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        currentlocation = findViewById(R.id.current);
        deliverylocation = findViewById(R.id.setlocation);

        currentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencurrentlocation();
                choose = "current";
            }
        });

        deliverylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendeliverylocation();
                choose = "delivery";
            }
        });
    }

    public void opencurrentlocation(){
        Intent intent = new Intent(this, CurrentLocationPick.class);
        startActivity(intent);
    }

    public void opendeliverylocation(){
        Intent intent = new Intent(this, SelectDeliveryLocation.class);
        startActivity(intent);
    }
}