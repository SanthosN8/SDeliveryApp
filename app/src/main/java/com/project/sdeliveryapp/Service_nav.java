package com.project.sdeliveryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;




public class Service_nav extends AppCompatActivity {
    Button next,back, camerabt;
    ImageView image;
    EditText editText,editTextPhone;
    public static String requireditem;
    public static String telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_nav);

        next = findViewById(R.id.nextbt);
        back = findViewById(R.id.backbt);
        image = findViewById(R.id.imagev);
        camerabt = findViewById(R.id.camerabt);
        editText = findViewById(R.id.editItems);
        editTextPhone = findViewById(R.id.editTextPhone);

        if(ContextCompat.checkSelfPermission(Service_nav.this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Service_nav.this,new String[]{Manifest.permission.CAMERA},101);
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
                requireditem = editText.getText().toString();
                telephone = editTextPhone.getText().toString();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openservice();
            }
        });
    }

    public void openseleclocation(){
        Intent intent = new Intent(this, SelectLocation.class);
        startActivity(intent);
    }

    public void openservice(){
        Intent intent = new Intent(this, ServiceSelection.class);
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