package com.example.farmingmanagemant;

import android.annotation.SuppressLint;
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

public class FarmerIntroductionPage2 extends AppCompatActivity {

    String phonenumber;
    EditText name, address, pincode, city, state;
    DataBase db;
    ImageButton next;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_introduction_page2);

        phonenumber = getIntent().getStringExtra("phonenumber");

        name = findViewById(R.id.phoneText);
        address = findViewById(R.id.phoneText1);
        pincode = findViewById(R.id.phoneText2);
        city = findViewById(R.id.phoneText5);
        state = findViewById(R.id.phoneText4);
        next = findViewById(R.id.nextButton);
        db = new DataBase(this);


        next.setOnClickListener(v -> {

            String coun = "India";
            String n = name.getText().toString();
            String a = address.getText().toString();
            String pin = pincode.getText().toString();
            String c = city.getText().toString();
            String s = state.getText().toString();

            if (n.isEmpty() || a.isEmpty() || pin.isEmpty() || c.isEmpty() || s.isEmpty()) {
                Toast.makeText(this, "Fill all necessary Field", Toast.LENGTH_SHORT).show();
            } else {

                if (phonenumber != null) {


                    db.addInfo(phonenumber, n, a, pin, c, s, coun);

                    Intent intent = new Intent(this, FarmerIntroductionPage3.class);
                    intent.putExtra("phonenumber", phonenumber);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Phone number is Null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}