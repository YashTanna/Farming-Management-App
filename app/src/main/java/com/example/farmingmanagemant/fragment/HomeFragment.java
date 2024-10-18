package com.example.farmingmanagemant.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.farmingmanagemant.R;

public class HomeFragment extends Fragment {

    private CardView garlic;
    private CardView watermelon;
    private CardView tamarind;
    private CardView turmeric;
    private CardView wheat;
    private CardView corn;
    private HorizontalScrollView horizontalScrollView;
    private Handler handler;
    private Runnable runnable;
    private int currentIndex = 0; // Track the current index of the image
    private int totalImages = 0; // Total number of images

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the HorizontalScrollView
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);

        // Initialize CardViews
        garlic = view.findViewById(R.id.garlic);
        watermelon = view.findViewById(R.id.watermelon);
        tamarind = view.findViewById(R.id.tamarind);
        turmeric = view.findViewById(R.id.turmeric);
        wheat = view.findViewById(R.id.wheat);
        corn = view.findViewById(R.id.corn);

        // Set up auto-scroll functionality
        setupAutoScroll();

        // Set click listeners for CardViews
        garlic.setOnClickListener(v -> loadFrag(new GarlicFragment()));
        watermelon.setOnClickListener(v -> loadFrag(new WatermelonFragment()));
        tamarind.setOnClickListener(v -> loadFrag(new TamarindFragment()));
        turmeric.setOnClickListener(v -> loadFrag(new TurmericFragment()));
        wheat.setOnClickListener(v -> loadFrag(new WheatFragment()));
        corn.setOnClickListener(v -> loadFrag(new CornFragment()));

        return view;
    }

    private void setupAutoScroll() {
        // Start the auto-scroll handler
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentIndex = (currentIndex + 1) % 7;
                horizontalScrollView.smoothScrollTo(currentIndex *  horizontalScrollView.getWidth(), 0);
                handler.postDelayed(this, 2000);
            }
        };

        // Start auto-scrolling after 5 seconds
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Remove the handler callbacks to stop auto-scrolling when the fragment is destroyed
        handler.removeCallbacks(runnable);
    }

    public void loadFrag(Fragment fragment) {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
