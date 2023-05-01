package com.project.sdeliveryapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.sdeliveryapp.databinding.ActivityDeliveryLocationPickBinding;

public class DeliveryLocationPick extends FragmentActivity implements OnMapReadyCallback ,GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private ActivityDeliveryLocationPickBinding binding;
    private LatLng mSelectedLatLng;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION =0;
    Button pickdelivery;
    TextView textView;
    public static String dlongti;
    public static String dlat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDeliveryLocationPickBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pickdelivery = findViewById(R.id.getdeliveryLocation);



        pickdelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensubmitrequest();
            }
        });




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            // Permission is already granted
            // Do something with the location
        }




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap = googleMap;
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        mSelectedLatLng = latLng;
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng));
        dlongti = String.valueOf(mSelectedLatLng.longitude);
        dlat = String.valueOf(mSelectedLatLng.latitude);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void opensubmitrequest(){
        Intent intent =null;
        String selectchoice = dashboard.select;

        if(selectchoice == "order") {
            intent = new Intent(DeliveryLocationPick.this, Submit.class);

        }else{
            intent = new Intent(DeliveryLocationPick.this, SubmitRequest.class);
        }
        startActivity(intent);
    }
}