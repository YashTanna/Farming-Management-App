package com.example.farmingmanagemant.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.farmingmanagemant.AdapterForRecycler;
import com.example.farmingmanagemant.ItemDatabase;
import com.example.farmingmanagemant.ItemModule;
import com.example.farmingmanagemant.R;

import java.util.ArrayList;

public class GarlicFragment extends Fragment {

    public GarlicFragment() {

    }

    RecyclerView recyclerView;
    ArrayList<ItemModule> list;
    ItemDatabase db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_garlic, container, false);
        db = new ItemDatabase(requireActivity());
        recyclerView = view.findViewById(R.id.recyclerView);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // Set LayoutManager
            // Set up your adapter and other RecyclerView settings here
        } else {
            Log.e("MainActivity2", "RecyclerView is null");
        }

        list = db.getItemFromDatabase("Garlic");

        AdapterForRecycler adapter =new AdapterForRecycler(requireActivity(),list);
        recyclerView.setAdapter(adapter);

        return view;
    }
}