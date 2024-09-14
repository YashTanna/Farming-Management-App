package com.example.farmingmanagemant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FarmerIntroductionPage1 extends AppCompatActivity {

    private TextView selectedOptionTextView;
//    private ScrollView scrollView;
//    private ImageView dropdownIcon;
//    private boolean isDropdownOpen = false;
    private EditText phone;
    private EditText discription;
    ImageButton next;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_introduction_page1);

//        selectedOptionTextView = findViewById(R.id.selectedOptionTextView);
        String phonenumber = getIntent().getStringExtra("phonenumber");
        next = findViewById(R.id.nextButton);
//        scrollView = findViewById(R.id.scrollView);
        discription = findViewById(R.id.phoneText2);
        db = new DataBase(this);
        phone = findViewById(R.id.phoneText);
        phone.setText(phonenumber);
        phone.setEnabled(false);
        next.setOnClickListener(v -> {

            String dis = discription.getText().toString();
            if(dis.isEmpty()){
                Toast.makeText(this,"Fill all necessary Field",Toast.LENGTH_SHORT).show();
            }else {

                if (phonenumber != null) {

                    if (discription == null) {
                        Toast.makeText(this, "Fill all necessary Field", Toast.LENGTH_SHORT).show();
                    }
                    String category = "Farmer";

                    db.addInfo(phonenumber, category, dis);
                    Intent intent = new Intent(this, FarmerIntroductionPage2.class);
                    intent.putExtra("phonenumber", phonenumber);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Phone number is Null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//dropdownIcon = findViewById(R.id.dropdownIcon);

        // Set the click listener for the dropdown icon
      //  dropdownIcon.setOnClickListener(v -> toggleDropdown());


    // Toggle the visibility of the dropdown (ScrollView)

    }

    //



