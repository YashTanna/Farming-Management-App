package com.example.farmingmanagemant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;
    List<DataClassVideo> dataList;

    public VideoAdapter(Context context, List<DataClassVideo> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataClassVideo data = dataList.get(position);
        holder.recImage.setImageResource(data.getImageResId());

        // Open YouTube video on clicking the image
        holder.recImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getUrl()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recImage = itemView.findViewById(R.id.recImage);
        }
    }
}

