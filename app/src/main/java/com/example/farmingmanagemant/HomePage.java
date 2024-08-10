package com.example.farmingmanagemant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePage extends AppCompatActivity {

    Button logout;
    Intent i1;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        i1 = new Intent(this, MainActivity.class);
        sp = getSharedPreferences("user_detail",MODE_PRIVATE);
        logout = findViewById(R.id.btn_logout);

        logout.setOnClickListener(v->{
            SharedPreferences.Editor edit = sp.edit();
            edit.clear();
            edit.commit();
            startActivity(i1);
            Toast.makeText(this,"Logout Successfully",Toast.LENGTH_SHORT).show();
        });

    }
}