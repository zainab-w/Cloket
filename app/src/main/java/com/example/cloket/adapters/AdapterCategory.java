package com.example.cloket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.HolderCategory>
{

    Context context;
    ArrayList<ModelCategory> categoryArrayList;
    HolderCategory holderCategory;


    public AdapterCategory(Context context, ArrayList<ModelCategory> categoryArrayList)
    {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }


    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.categoryrows,parent, false);

        return new HolderCategory(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategory holder, int position)
    {

        ModelCategory model = categoryArrayList.get(position);

        String category = model.getCategory();
        String limit = model.getLimit();

        holder.CategoryTV.setText(category);
        holder.CategoryLimitET.setText(limit);


    }

    @Override
    public int getItemCount()
    {
        return categoryArrayList.size();
    }


    class HolderCategory extends RecyclerView.ViewHolder
    {

        TextView CategoryTV;
        TextView CategoryLimitET;


        public HolderCategory(@NonNull View itemView) {
            super(itemView);
            CategoryTV = itemView.findViewById(R.id.CategoryTV);
            CategoryLimitET = itemView.findViewById(R.id.CategoryLimitET);

        }

    }
}

