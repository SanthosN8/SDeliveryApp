package com.project.sdeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.b_search);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.b_home:
                        startActivity(new Intent(getApplicationContext(),dashboard.class));
                        overridePendingTransition(0,0);
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
    }
}