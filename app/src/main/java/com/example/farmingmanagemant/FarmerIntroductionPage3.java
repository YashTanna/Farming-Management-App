package com.example.farmingmanagemant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FarmerIntroductionPage3 extends AppCompatActivity {

    EditText website,email,facebook,instagram;
    ImageButton next;
    String phonenumber;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_introduction_page3);

        website = findViewById(R.id.phoneText);
        email = findViewById(R.id.phoneText1);
        facebook = findViewById(R.id.phoneText2);
        instagram = findViewById(R.id.phoneText5);
        phonenumber = getIntent().getStringExtra("phonenumber");
        db = new DataBase(this);

        next = findViewById(R.id.nextButton);

        next.setOnClickListener(v -> {

            String web = website.getText().toString();
            String em = email.getText().toString();
            String face = facebook.getText().toString();
            String insta = instagram.getText().toString();

            if(em.isEmpty()){
                Toast.makeText(this, "Fill all necessary Field", Toast.LENGTH_SHORT).show();
            }else {

                db.addInfo(phonenumber, web, em, face, insta);
                Intent intent = new Intent(this, HomePage.class);
                intent.putExtra("phonenumber", phonenumber);
                startActivity(intent);
            }
        });

    }
}