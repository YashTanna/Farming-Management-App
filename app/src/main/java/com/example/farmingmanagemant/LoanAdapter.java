package com.example.farmingmanagemant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LoanAdapter extends RecyclerView.Adapter<LoanViewHolder> {

    Context context;
    List<Lone_item> items;

    public LoanAdapter(Context context, List<Lone_item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public LoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loan_item_view, parent, false);
        return new LoanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanViewHolder holder, int position) {
        // Set the data for each item
        holder.loan_name.setText(items.get(position).getLoan_name());
        holder.loan_description.setText(items.get(position).getLoan_description());
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
