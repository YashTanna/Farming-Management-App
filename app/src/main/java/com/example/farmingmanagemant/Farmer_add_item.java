package com.example.farmingmanagemant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Farmer_add_item extends AppCompatActivity {

    EditText name,price,location,time;
    Button btn;
    Toolbar toolbar;
    ItemDatabase db;
    AutoCompleteTextView dropdown;
    ArrayAdapter<String> adapter;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_add_item);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        location = findViewById(R.id.location);
        time = findViewById(R.id.time);
        btn = findViewById(R.id.btn);
        sp = getSharedPreferences("login",MODE_PRIVATE);

//        Adding Drop Down
        dropdown = findViewById(R.id.dropdown);

        String [] crops = {"Garlic","Watermelon","Tamarind","Turmeric","Wheat","Corn"};

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new ArrayAdapter<>(this,R.layout.crop_type_dropdown,crops);
        dropdown.setAdapter(adapter);

        final String[] t = new String[1];
        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                t[0] = adapterView.getItemAtPosition(i).toString();
                Log.d("AddItem", "Added Item : "+t[0]);
            }
        });

        // Enable the back arrow (home as up)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String number = getIntent().getStringExtra("phonenumber");
        db = new ItemDatabase(this);

        btn.setOnClickListener(v -> {
            String n = name.getText().toString();
            String p = price.getText().toString();
            String l = location.getText().toString();
            String ti = time.getText().toString();

            if(n.isEmpty() || t[0].isEmpty() || p.isEmpty() || l.isEmpty() || ti.isEmpty()){
                Toast.makeText(this,"Enter all details",Toast.LENGTH_SHORT).show();
            }else{

                ItemModule data = new ItemModule(number,n, t[0],p,l,ti);
                name.setEnabled(false);
                name.setText(n);
                db.addItemToDatabase(data);
            }

            Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
            price.setText("");
        });
    }
    public void onBackPressed() {
        // Call the superclass method to handle default back press behavior
        super.onBackPressed();

        // Create an Intent to navigate to Login activity
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.commit();
        Intent intent = new Intent(Farmer_add_item.this, FirstPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the back stack and create a new task
        startActivity(intent);
        finish(); // Close the Farmer_add_item activity
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Create an Intent to navigate to the LoginActivity
            Intent intent = new Intent(this, Login.class);  // Replace LoginActivity with your activity
            startActivity(intent);
            finish();  // Close the current activity
            return true;
        } else if (item.getItemId() == R.id.logout_file) {

            SharedPreferences.Editor edit = sp.edit();
            edit.clear();
            edit.commit();
            Intent intent = new Intent(this, FirstPage.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_file,menu);
        return true;
    }
}