package com.example.farmingmanagemant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    TextView email,pass;
    Button btn_login;
    Intent intent;
    MyDataType mydatatype;
    MyDBHelper mydbhelper;
    SharedPreferences sp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Enable the Up button (back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Handle the back button press
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        email = findViewById(R.id.et_email);
        pass = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        intent = new Intent(this,HomePage.class);
        mydatatype = new MyDataType();
        mydbhelper = new MyDBHelper(this);
        sp = getSharedPreferences("user_detail",MODE_PRIVATE);


        btn_login.setOnClickListener(v -> {

            if(email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){

                Toast.makeText(this,"Enter Email and Password",Toast.LENGTH_SHORT).show();

            }else {

                mydatatype.email = email.getText().toString();
                mydatatype.pass = pass.getText().toString();
                boolean exist = mydbhelper.userAuthentication(mydatatype);
                if(exist){
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("email",mydatatype.email);
                    edit.putString("password", mydatatype.pass);
                    edit.commit();

                    startActivity(intent);
                    Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Wrong Email or Password",Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}