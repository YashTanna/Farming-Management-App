package com.example.farmingmanagemant.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.farmingmanagemant.R;

public class HomeFragment extends Fragment {

    CardView garlic;
    CardView watermelon;
    CardView tamarind;
    CardView turmeric;
    CardView wheat;
    CardView corn;

    public HomeFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        garlic = view.findViewById(R.id.garlic);
        watermelon = view.findViewById(R.id.watermelon);
        tamarind = view.findViewById(R.id.tamarind);
        turmeric = view.findViewById(R.id.turmeric);
        wheat = view.findViewById(R.id.wheat);
        corn = view.findViewById(R.id.corn);

        garlic.setOnClickListener(v -> {
            loadFrag(new GarlicFragment());
        });

        watermelon.setOnClickListener(v -> {
            loadFrag(new WatermelonFragment());
        });

        tamarind.setOnClickListener(v -> {
            loadFrag(new TamarindFragment());
        });

        turmeric.setOnClickListener(v -> {
            loadFrag(new TurmericFragment());
        });

        wheat.setOnClickListener(v -> {
            loadFrag(new WheatFragment());
        });

        corn.setOnClickListener(v -> {
            loadFrag(new CornFragment());
        });

        return view;
    }

    public void loadFrag(Fragment fragment){
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}