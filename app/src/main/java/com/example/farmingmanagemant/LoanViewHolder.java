package com.example.farmingmanagemant;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LoanViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView loan_name, loan_description;

    public LoanViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        loan_name = itemView.findViewById(R.id.loan_name);
        loan_description = itemView.findViewById(R.id.loan_description);
    }
}
