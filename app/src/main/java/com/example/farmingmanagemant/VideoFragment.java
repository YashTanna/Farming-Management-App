package com.example.farmingmanagemant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmingmanagemant.DataClassVideo;
import com.example.farmingmanagemant.R;
import com.example.farmingmanagemant.VideoAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    RecyclerView recyclerView;
    List<DataClassVideo> dataList;
    VideoAdapter adapter;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        // Add video data with YouTube links
        dataList.add(new DataClassVideo("1", R.drawable.video1, "https://www.youtube.com/watch?app=desktop&v=aQ6dQNiogJA"));
        dataList.add(new DataClassVideo("2", R.drawable.video2, "https://www.youtube.com/watch?v=zfHDkS31-0g"));
        dataList.add(new DataClassVideo("3", R.drawable.video3, "https://www.youtube.com/watch?app=desktop&v=W3P9deLFkk8"));
        dataList.add(new DataClassVideo("4", R.drawable.video4, "https://www.youtube.com/watch?app=desktop&v=heTxEsrPVdQ"));
        dataList.add(new DataClassVideo("5", R.drawable.video5, "https://www.youtube.com/watch?app=desktop&v=4EnjwyTc_2E"));
        adapter = new VideoAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
