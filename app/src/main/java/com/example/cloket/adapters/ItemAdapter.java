package com.example.cloket.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cloket.R;
import com.example.cloket.models.ItemsModel;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.HolderItems>
{
    public static final long MAX_BYTES_IMG = 50000000;
     Context context;
    //array list to hold the data from ItemsModel
     ArrayList<ItemsModel> itemsArrayList;
    HolderItems holderItems;
    static final String TAG = "IMG_ADAPTER_TAG";
    ProgressBar progressBar;

    //constructor
    public ItemAdapter(Context context, ArrayList<ItemsModel> itemsArrayList) {
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @NonNull
    @Override
    public HolderItems onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.items,parent, false);
        return new HolderItems(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderItems holder, int position)
    {


        ItemsModel model = itemsArrayList.get(position);

        //get data
        String name = model.getName();
        String description = model.getDescription();
        String date = model.getDate();
        String URL = model.getURL();

        //set data
        Glide.with(context)
                .load(model.getURL())
                .centerCrop()
                .placeholder(R.drawable.ic_gallery)
                .into(holder.itemImageView);

        holder.itemNameTV.setText(name);
        holder.itemDescTV.setText(description);
        holder.dateAddedTV.setText(date);

    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size(); //return number of items
    }

    //View holder class for items.xml
    class HolderItems extends RecyclerView.ViewHolder
    {
        //UI views of items.xml
        ImageView itemImageView;
        TextView itemNameTV,itemDescTV,dateAddedTV;


        public HolderItems(@NonNull View itemView)
        {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.itemImageView);
            itemNameTV = itemView.findViewById(R.id.itemNameTV);
            itemDescTV = itemView.findViewById(R.id.itemDescTV);
            dateAddedTV = itemView.findViewById(R.id.dateAddedTV);

        }
    }
}