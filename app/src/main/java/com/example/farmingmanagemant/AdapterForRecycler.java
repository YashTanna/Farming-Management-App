package com.example.farmingmanagemant;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URLEncoder;
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
        DataBase db = new DataBase(context);
        SharedPreferences sp = context.getSharedPreferences("login",Context.MODE_PRIVATE);
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

        holder.whatsapp.setOnClickListener(v -> {
            String whatsappNumber = data.number;
            String custNumber = sp.getString("number",null);
            MyDataType md = db.getInfo(custNumber);

            String message = "CUSTOMER INFORMATION: \n\nName: "+md.name+"\nNumber: "+md.phonenumber+"\nEmail: "+md.email+"\nAddress: "+md.address
                    +"\nCity: "+md.city+"\n\nORDER INFORMATIO : \n\nType: "+data.type+"\nPrice: "+data.price;

            String number = "+91 "+whatsappNumber;
            try{
                String encodedMessage = URLEncoder.encode(message, "UTF-8");

                // Construct the WhatsApp URL with phone number and message
                String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + encodedMessage;

                // Create an intent to open WhatsApp with the pre-filled message
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(i);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {

        TextView type,name,price,location,time;
        ImageView whatsapp;
        public ViewHold(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.id_type);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            location = itemView.findViewById(R.id.location);
            time = itemView.findViewById(R.id.time);
            whatsapp = itemView.findViewById(R.id.whatsapp);
        }
    }
}
