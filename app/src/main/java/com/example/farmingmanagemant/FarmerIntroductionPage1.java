package com.example.farmingmanagemant;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class FarmerIntroductionPage1 extends AppCompatActivity {

    private TextView selectedOptionTextView;
    private ScrollView scrollView;
    private ImageView dropdownIcon;
    private boolean isDropdownOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_information);

      //  selectedOptionTextView = findViewById(R.id.selectedOptionTextView);
        scrollView = findViewById(R.id.scrollView);
//dropdownIcon = findViewById(R.id.dropdownIcon);

        // Set the click listener for the dropdown icon
      //  dropdownIcon.setOnClickListener(v -> toggleDropdown());
    }

    // Toggle the visibility of the dropdown (ScrollView)

    }

    //



