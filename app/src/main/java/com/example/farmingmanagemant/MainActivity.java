package com.example.farmingmanagemant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_login,btn_signup;
    Intent i1,i2,i3;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i1 = new Intent(this,Login.class);
//        i2 = new Intent(this,SignUp.class);
        i3 = new Intent(this,HomePage.class);

        sp = getSharedPreferences("user_detail",MODE_PRIVATE);
        if(sp.contains("email") && sp.contains("password")){
            i3 = new Intent(this,HomePage.class);
            startActivity(i3);
        }

        btn_login =  findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v->{
            startActivity(i1);
        });

        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(v->{
            startActivity(i2);
        });

    }
}