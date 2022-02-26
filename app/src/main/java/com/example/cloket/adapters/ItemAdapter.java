package com.example.cloket;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class ItemAdapter  extends FirebaseRecyclerAdapter<
        ItemsModel, ItemAdapter.personsViewholder> {

    public ItemAdapter(
            @NonNull FirebaseRecyclerOptions<ItemsModel> options)
    {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull ItemsModel model)
    {

        holder.itemNameTV.setText(model.getName());


        holder.itemDescTV.setText(model.getDesc());


        holder.date.setText(model.getDate());
    }


    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);
        return new ItemAdapter.personsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView itemNameTV, itemDescTV, date;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            itemNameTV = itemView.findViewById(R.id.itemNameTV);
            itemDescTV = itemView.findViewById(R.id.itemDescTV);
            date = itemView.findViewById(R.id.date);
        }
    }
}