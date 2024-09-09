package com.example.farmingmanagemant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    TextView fname,lname,email,number,pass,confpass;
    Button btn_signup;
    MyDataType mydatatype;
    DataBase mydbhelper;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button (back button)
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Handle the back button press
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        fname = findViewById(R.id.et_fname);
        lname = findViewById(R.id.et_lname);
        email = findViewById(R.id.et_email);
        number = findViewById(R.id.et_number);
        btn_signup = findViewById(R.id.btn_submit);
        pass = findViewById(R.id.et_pass);
        confpass = findViewById(R.id.et_confpass);
        mydatatype = new MyDataType();
        mydbhelper = new DataBase(this);
        intent = new Intent(this,Login.class);

        btn_signup.setOnClickListener(v->{
            if(!pass.getText().toString().equals(confpass.getText().toString())){
                confpass.setError("Password does not match");
                Toast.makeText(this,"Password and Confirm Password should be same",Toast.LENGTH_SHORT).show();
            }else{
                mydatatype.fname = fname.getText().toString();
                mydatatype.lname = lname.getText().toString();
                mydatatype.email = email.getText().toString();
                mydatatype.phone = number.getText().toString();
                mydatatype.pass = confpass.getText().toString();
               // mydbhelper.addInfo(mydatatype);
                Toast.makeText(this,"Account created Successfully",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}