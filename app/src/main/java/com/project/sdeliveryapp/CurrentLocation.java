package com.project.sdeliveryapp;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;




public class CurrentLocation extends AppCompatActivity {
    public Button locationbt;
    public static String loclat;
    public static String loclon;

    DatabaseHelper db;


    FusedLocationProviderClient mFusedLocationClient;
    //variable
    TextView latitude, longitude;
    int PERMISSION_ID = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);
        db = new DatabaseHelper(this);

        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longtitude);
        locationbt = findViewById(R.id.locate);




        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //  get the location
        getLastLocation();





    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {

        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {


                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();

                        } else {
                            latitude.setText(location.getLatitude() + "");
                            longitude.setText(location.getLongitude() + "");
                            locationbt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //String insert = String.valueOf(db.insertlocationdata(location.getLatitude(),location.getLongitude()));
                                    loclat = String.valueOf(location.getLatitude());
                                    loclon = String.valueOf(location.getLongitude());
                                    openSubmit();

                                    Toast.makeText(CurrentLocation.this, "Location Successfull Shared", Toast.LENGTH_SHORT).show();
                                }
                            });




                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {

            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }


    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude.setText("Latitude: " + mLastLocation.getLatitude() + "");
            longitude.setText("Longitude: " + mLastLocation.getLongitude() + "");






        }
    };

    // check permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;


    }

    // request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }


    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }

    public void openSubmit() {

        Intent intent =null;
        String selectchoice = dashboard.select;

        if(selectchoice == "request") {
            intent = new Intent(CurrentLocation.this, SubmitRequest.class);

        }else{
            intent = new Intent(CurrentLocation.this, Submit.class);
        }
        startActivity(intent);
    }
        
   





}
