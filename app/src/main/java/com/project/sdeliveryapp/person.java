package com.project.sdeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class person extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView profilename,phinsert,mailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);



        profilename = findViewById(R.id.profilename);
        profilename.setText(login.profileName);
        phinsert = findViewById(R.id.phnoinsert);
        //phinsert.setText(login.sendphno);
        mailid = findViewById(R.id.mailid);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.b_person);

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
                        return true;

                }
                return true;
            }
        });
    }
}