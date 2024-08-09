package com.example.farmingmanagemant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button b,b2;
    Intent intent,i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b =  findViewById(R.id.btn_login);
        intent = new Intent(this,Login.class);

        b.setOnClickListener(v->{
            startActivity(intent);
        });

        b2 = findViewById(R.id.btn_signup);

        i2 = new Intent(this,SignUp.class);

        b2.setOnClickListener(v->{
            startActivity(i2);
        });

    }
}