package com.example.farmingmanagemant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Loan extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    LoanAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Loan.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        // Pass the URL for each loan item
        androidData = new DataClass("Kisan Credit Card", R.string.KCC, "Next", R.drawable.kishan_credit_card, getString(R.string.KCC1));
        dataList.add(androidData);

        androidData = new DataClass("Kisan Samriddhi Rin", R.string.KSR, "Next", R.drawable.kishan_samriddhi_rin, getString(R.string.KSR1));
        dataList.add(androidData);

        androidData = new DataClass("PM Kusum", R.string.Kusum, "Next", R.drawable.pm_kusum, getString(R.string.Kusum1));
        dataList.add(androidData);

        androidData = new DataClass("AHIDF", R.string.ahidf, "Next", R.drawable.ahidf, getString(R.string.ahidf1));
        dataList.add(androidData);

        androidData = new DataClass("AIF", R.string.aif, "Next", R.drawable.aif, getString(R.string.aif1));
        dataList.add(androidData);

        adapter = new LoanAdapter(Loan.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text){
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList){
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
