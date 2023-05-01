package com.project.sdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceSelection extends AppCompatActivity {
    Button backbt, nextbt, selectbt;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView viewradio;
    EditText editsname;
    public static String servename;
    public static String selectradio;
    public static String selectr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_selection);
        backbt = findViewById(R.id.backbt);
        nextbt = findViewById(R.id.nextbt);
        editsname = findViewById(R.id.editsname);

        radioGroup = findViewById(R.id.radioGroup);
        viewradio = findViewById(R.id.viewradio);
        selectbt = findViewById(R.id.select);

        selectbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                viewradio.setText(radioButton.getText());

                servename = editsname.getText().toString();
                selectradio = radioButton.getText().toString();


            }
        });

        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboard();
            }
        });

        nextbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openServicenav();

            }
        });


    }

    public void opendashboard() {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);

    }

    public void openServicenav() {
        Intent intent = new Intent(this, Service_nav.class);
        startActivity(intent);

    }

    public void checkBt(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Radio Button: " +radioButton.getText() , Toast.LENGTH_SHORT).show();
    }

}