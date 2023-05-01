package com.project.sdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class EndActivity extends AppCompatActivity  {
     DataBHelper dbh;
     Button vieworder,gohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        dbh = new DataBHelper(this);
        vieworder = findViewById(R.id.viewdorder);
        gohome = findViewById(R.id.closedview);
        dbh.resetAutoIncrementCounter(dbh.getWritableDatabase(), "userdetailstable");

       gohome.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               opendashboard();
           }
       });

       vieworder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Cursor result = dbh.getLastRow();

               if(result != null && result.moveToFirst()){
                   @SuppressLint("Range") String name = result.getString(result.getColumnIndex("name"));
                   @SuppressLint("Range") String item = result.getString(result.getColumnIndex("item"));
                   @SuppressLint("Range") String phone = result.getString(result.getColumnIndex("phone"));
                   @SuppressLint("Range") String latitude = result.getString(result.getColumnIndex("latitude"));
                   @SuppressLint("Range") String longtitude = result.getString(result.getColumnIndex("longtitude"));

                   AlertDialog.Builder builder = new AlertDialog.Builder(EndActivity.this);
                   builder.setTitle("Order Details");
                   builder.setMessage("Name " + name + "\nItem: " + item + "\nPhone: " + phone + "\nLatitude: " + latitude + "\nLongtitude: " + longtitude);
                   builder.setPositiveButton("OK", null);
                   builder.show();

               }
               if(result != null){
                   result.close();
               }
               dbh.close();
           }
       });
    }
    public void opendashboard(){
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }


}