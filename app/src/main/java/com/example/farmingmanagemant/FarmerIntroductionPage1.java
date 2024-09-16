package com.example.farmingmanagemant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FarmerIntroductionPage1 extends AppCompatActivity {

    private TextView selectedOptionTextView;
    //private ScrollView scrollView;
    private ImageView dropdownIcon;
    private boolean isDropdownOpen = false;
    private EditText phone;
    private EditText discription;
    ImageButton next;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_introduction_page1);

        // Initialize views
        selectedOptionTextView = findViewById(R.id.phoneText1);  // TextView where the selected category will be shown
       // scrollView = findViewById(R.id.scrollView);              // ScrollView with category options
       // dropdownIcon = findViewById(R.id.image1);                // Dropdown icon to toggle the ScrollView
        discription = findViewById(R.id.phoneText2);             // EditText for description
        phone = findViewById(R.id.phoneText);                    // Phone number field
        next = findViewById(R.id.nextButton);                    // Next button
        db = new DataBase(this);                         // Database object

        String phonenumber = getIntent().getStringExtra("phonenumber");
        phone.setText(phonenumber);
        phone.setEnabled(false);

        // Hide ScrollView initially
        //scrollView.setVisibility(View.GONE);

        // Toggle dropdown visibility on dropdown icon click
        //dropdownIcon.setOnClickListener(v -> toggleDropdown());

        // Handle option selection from ScrollView
        //findViewById(R.id.option1).setOnClickListener(v -> selectCategory("Farmer"));
      //  findViewById(R.id.option2).setOnClickListener(v -> selectCategory("Customer"));

        // Next button click listener
        next.setOnClickListener(v -> {
            String dis = discription.getText().toString();
            String category = selectedOptionTextView.getText().toString(); // Selected category

            if (dis.isEmpty() || category.equals("select category")) {
                Toast.makeText(this, "Fill all necessary fields", Toast.LENGTH_SHORT).show();
            } else {
                if (phonenumber != null) {
                    db.addInfo(phonenumber, category, dis); // Add info to the database
                    Intent intent = new Intent(this, FarmerIntroductionPage2.class);
                    intent.putExtra("phonenumber", phonenumber);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Phone number is Null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to toggle dropdown (ScrollView)
//    private void toggleDropdown() {
//        if (isDropdownOpen) {
//            scrollView.setVisibility(View.GONE);
//            isDropdownOpen = false;
//        } else {
//            scrollView.setVisibility(View.VISIBLE);
//            isDropdownOpen = true;
//        }
//    }
//
//    // Method to handle category selection
//    private void selectCategory(String category) {
//        selectedOptionTextView.setText(category); // Set selected category text
//        scrollView.setVisibility(View.GONE);      // Hide the dropdown after selection
//        isDropdownOpen = false;
//    }
}


