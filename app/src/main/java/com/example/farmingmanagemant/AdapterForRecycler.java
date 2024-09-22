package com.example.farmingmanagemant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterForRecycler extends RecyclerView.Adapter<AdapterForRecycler.ViewHold> {

    Context context;
    ArrayList<ItemModule> list;

    public AdapterForRecycler(Context context,ArrayList<ItemModule>list){
        this.context = context;
        this.list = list;
        if(list == null){
            Log.d("Adapter","List is Empty");
        }
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card,parent,false);
        return new ViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {

        ItemModule data =list.get(position);
        Log.d("Adapter", "Binding item at position: " + position);
        Log.d("Adapter", "Item data: " + data.type + ", " + data.name);
        if(list.isEmpty()){
            Log.d("Adapter", "List is empty");
        }
        holder.type.setText(data.type);
        holder.time.setText(data.time);
        holder.price.setText(data.price);
        holder.name.setText(data.name);
        holder.location.setText(data.location);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {

        TextView type,name,price,location,time;
        public ViewHold(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.id_type);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            location = itemView.findViewById(R.id.location);
            time = itemView.findViewById(R.id.time);
        }
    }
}
