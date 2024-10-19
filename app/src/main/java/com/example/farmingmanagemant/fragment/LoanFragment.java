package com.example.farmingmanagemant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmingmanagemant.DataClassLoan;
import com.example.farmingmanagemant.LoanAdapter;
import com.example.farmingmanagemant.R;

import java.util.ArrayList;
import java.util.List;

public class LoanFragment extends Fragment {

    RecyclerView recyclerView;
    List<DataClassLoan> dataList;
    LoanAdapter adapter;
    DataClassLoan androidData;
    SearchView searchView;

    public LoanFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_loan, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        searchView = view.findViewById(R.id.search);

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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        // Pass the URL for each loan item
        androidData = new DataClassLoan("Kisan Credit Card", R.string.KCC, "Next", R.drawable.kishan_credit_card, getString(R.string.KCC1));
        dataList.add(androidData);

        androidData = new DataClassLoan("Kisan Samriddhi Rin", R.string.KSR, "Next", R.drawable.kishan_samriddhi_rin, getString(R.string.KSR1));
        dataList.add(androidData);

        androidData = new DataClassLoan("PM Kusum", R.string.Kusum, "Next", R.drawable.pm_kusum, getString(R.string.Kusum1));
        dataList.add(androidData);

        androidData = new DataClassLoan("AHIDF", R.string.ahidf, "Next", R.drawable.ahidf, getString(R.string.ahidf1));
        dataList.add(androidData);

        androidData = new DataClassLoan("AIF", R.string.aif, "Next", R.drawable.aif, getString(R.string.aif1));
        dataList.add(androidData);

        adapter = new LoanAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void searchList(String text) {
        List<DataClassLoan> dataSearchList = new ArrayList<>();
        for (DataClassLoan data : dataList) {
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
