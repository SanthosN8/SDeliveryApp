package com.project.sdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectDeliveryLocation extends AppCompatActivity {
    Button manual,pick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_delivery_location);

        manual = findViewById(R.id.manual);
        pick = findViewById(R.id.pick);

        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendeliverylocationmanual();
            }
        });

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendeliverylocationpick();
            }
        });
    }
    public void opendeliverylocationpick(){
        Intent intent = new Intent(this, DeliveryLocationPick.class);
        startActivity(intent);
    }
    public void opendeliverylocationmanual(){
        Intent intent = new Intent(this, DeliveryLocation.class);
        startActivity(intent);
    }

}