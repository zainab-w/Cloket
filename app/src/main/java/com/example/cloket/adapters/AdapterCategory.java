package com.example.cloket.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloket.R;
import com.example.cloket.itemsView;
import com.example.cloket.models.ModelCategory;

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
        String goal = model.getGoal();
        String id = model.getId();

        holder.CategoryTV.setText(category);
        holder.CategoryLimitET.setText(goal);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, itemsView.class);
                intent.putExtra("categoryId", id);
                intent.putExtra("categoryTitle", category);
                context.startActivity(intent);
            }
        });

//        holder.deleteCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //confirm delete
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Delete")
//                        .setMessage("Are you sure you want to delete this category?")
//                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which)
//                            {
//                                Toast.makeText(context, "Deleting...", Toast.LENGTH_SHORT).show();
//                                deleteCategory(model, holder);
//                            }
//                        })
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which)
//                            {
//                                dialog.dismiss();
//                            }
//                        })
//                        .show();
//            }
//        });

    }

//    private void deleteCategory(ModelCategory model, HolderCategory holder)
//    {
//        //get the id of the category to delete
//        String id = model.getId();
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
//        ref.child(id)
//                .removeValue()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        //category deleted successfully
//                        Toast.makeText(context, "Successfully deleted!", Toast.LENGTH_SHORT).show();
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        //Error in deleting category
//                        Toast.makeText(context, "" + e.getMessage() , Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    @Override
    public int getItemCount()
    {
        return categoryArrayList.size();
    }


    class HolderCategory extends RecyclerView.ViewHolder
    {

        TextView CategoryTV;
        TextView CategoryLimitET,amount;
//        ImageButton deleteCategory;

        public HolderCategory(@NonNull View itemView) {
            super(itemView);
            CategoryTV = itemView.findViewById(R.id.CategoryTV);
            CategoryLimitET = itemView.findViewById(R.id.CategoryLimitET);
            amount = itemView.findViewById(R.id.amount);
//            deleteCategory = itemView.findViewById(R.id.deleteCategory);
        }

    }
}

