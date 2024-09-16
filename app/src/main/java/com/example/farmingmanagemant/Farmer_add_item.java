package com.example.farmingmanagemant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Farmer_add_item extends AppCompatActivity {

    EditText name,type,price,location,time;
    Button btn;
    ItemDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_add_item);

        name = findViewById(R.id.name);
        type = findViewById(R.id.type);
        price = findViewById(R.id.price);
        location = findViewById(R.id.location);
        time = findViewById(R.id.time);
        btn = findViewById(R.id.btn);
        String number = getIntent().getStringExtra("phonenumber");
        db = new ItemDatabase(this);

        btn.setOnClickListener(v -> {
            String n = name.getText().toString();
            String t = type.getText().toString().toUpperCase();
            String p = price.getText().toString();
            String l = location.getText().toString();
            String ti = time.getText().toString();

            if(n.isEmpty() || t.isEmpty() || p.isEmpty() || l.isEmpty() || ti.isEmpty()){
                Toast.makeText(this,"Enter all details",Toast.LENGTH_SHORT).show();
            }else{

                ItemModule data = new ItemModule(number,n,t,p,l,ti);
                name.setEnabled(false);
                name.setText(n);
                db.addItemToDatabase(data);
            }
        });
    }
    public void onBackPressed() {
        // Call the superclass method to handle default back press behavior
        super.onBackPressed();

        // Create an Intent to navigate to Login activity
        Intent intent = new Intent(Farmer_add_item.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the back stack and create a new task
        startActivity(intent);
        finish(); // Close the Farmer_add_item activity
    }
}