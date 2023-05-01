package com.project.sdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText username, email,phone, password, cpassword;
    Button signup;
    DBHelper DB;
    public static String mail;
    public static  String phnoinsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        signup = findViewById(R.id.signup);
        DB = new DBHelper(this);

        //send profile
        mail = email.getText().toString();
        phnoinsert = phone.getText().toString();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String emailid = email.getText().toString();
                String phoneno = phone.getText().toString();
                String pass = password.getText().toString();
                String cpass = cpassword.getText().toString();

                if(TextUtils.isEmpty(user)|| TextUtils.isEmpty(emailid)||TextUtils.isEmpty(phoneno)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(cpass)){
                    Toast.makeText(signup.this, "All Fields Required", Toast.LENGTH_SHORT).show();

                }
                else{
                    if(pass.equals(cpass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertdata(user,pass,emailid,phoneno);
                            if(insert==true){
                                Toast.makeText(signup.this, "Signup is Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), login.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(signup.this, "Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(signup.this, "Username exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(signup.this, "Password Not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}