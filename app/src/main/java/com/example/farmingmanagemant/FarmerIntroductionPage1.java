package com.example.farmingmanagemant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FarmerIntroductionPage1 extends AppCompatActivity {

    private ImageView dropdownIcon;
    private EditText category;
    private EditText phone;
    private EditText discription;
    ImageButton next;
    DataBase db;
    AutoCompleteTextView dropdown;
    ArrayAdapter<String> adapter;
    String cat;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_introduction_page1);

        discription = findViewById(R.id.phoneText2);
        phone = findViewById(R.id.phoneText);
        next = findViewById(R.id.nextButton);
        db = new DataBase(this);
        String phonenumber = getIntent().getStringExtra("phonenumber");
        phone.setText(phonenumber);
        phone.setEnabled(false);
        dropdown = findViewById(R.id.dropdown);
        sp = getSharedPreferences("login",MODE_PRIVATE);

//        Initializing DropDown

        String [] category = {"Farmer","Customer"};

        adapter = new ArrayAdapter<>(this,R.layout.crop_type_dropdown,category);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cat = adapterView.getItemAtPosition(i).toString();
            }
        });

        SharedPreferences.Editor edit = sp.edit();
        edit.putString("number",phonenumber);


        next.setOnClickListener(v -> {
            String dis = discription.getText().toString();
            edit.putString("category",cat);
            Log.d("Category Selected", "Category: " + cat);
            edit.commit();
            if (dis.isEmpty() || cat.isEmpty()) {
                Toast.makeText(this, "Fill all necessary fields", Toast.LENGTH_SHORT).show();
            } else {
                if(cat.equals("Farmer")){

                    Intent intent = new Intent(this,Farmer_add_item.class);
                    intent.putExtra("phonenumber",phonenumber);
                    startActivity(intent);
                }else if(cat.equals("Customer")){

                    db.addInfo(phonenumber,cat,dis);

                    Intent intent = new Intent(this, FarmerIntroductionPage2.class);
                    intent.putExtra("phonenumber",phonenumber);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"Enter either Farmer or Customer",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


