package com.example.farmingmanagemant;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Loan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Creating a list of loan items
        List<Lone_item> items = new ArrayList<>();
        items.add(new Lone_item("Farmer Loan", "This loan is for farmers who have more needs.", R.drawable.india));
        items.add(new Lone_item("Agri Loan", "This loan is for agricultural purposes.", R.drawable.india));
        // Add more items as needed

        // Setting up the adapter
        LoanAdapter adapter = new LoanAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }
}
