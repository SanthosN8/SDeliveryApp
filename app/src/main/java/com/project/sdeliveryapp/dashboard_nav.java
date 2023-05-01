package com.project.sdeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
//camera
import android.Manifest;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class dashboard_nav extends AppCompatActivity {
    Button next,back, camerabt;
    EditText editPersonName, item,phno;
    ImageView image;
    public static String uname;
    public static String items;
    public static String phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_nav);
        next = findViewById(R.id.next);
        back = findViewById(R.id.back);
        image = findViewById(R.id.image);
        camerabt = findViewById(R.id.camera);
        editPersonName = findViewById(R.id.editPersonName);
        item = findViewById(R.id.editItems);
        phno = findViewById(R.id.editPhoneno);







     if(ContextCompat.checkSelfPermission(dashboard_nav.this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
         ActivityCompat.requestPermissions(dashboard_nav.this,new String[]{Manifest.permission.CAMERA},101);
     }

     camerabt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(intent,101);
         }
     });




    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            openseleclocation();
            uname = String.valueOf(editPersonName.getText());
            items = item.getText().toString();
            phoneno = phno.getText().toString();

        }
    });

    back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            opendashboard();
        }
    });



    }
    public void openseleclocation(){
        Intent intent = new Intent(this, SelectLocation.class);
        startActivity(intent);
    }

    public void opendashboard(){
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
        }
    }


}