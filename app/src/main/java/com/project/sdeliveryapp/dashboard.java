package com.project.sdeliveryapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationBarView;


public class dashboard extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    public static String select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.b_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.b_home:
                        return true;
                    case R.id.b_review:
                        startActivity(new Intent(getApplicationContext(), review.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.b_person:
                        startActivity(new Intent(getApplicationContext(),person.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return true;
            }
        });

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboardcontent();
                select = "order";
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboardcontent();
                select = "order";
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboardservicecontent();
                select = "request";
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboardservicecontent();
                select = "request";

            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboardservicecontent();
                select = "request";
            }
        });

    }

    public void opendashboardcontent(){
        Intent intent = new Intent(this, dashboard_nav.class);
        startActivity(intent);
    }

    public void opendashboardservicecontent(){
        Intent intent = new Intent(this, ServiceSelection.class);
        startActivity(intent);
    }
}