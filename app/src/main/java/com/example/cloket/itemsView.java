package com.example.cloket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloket.adapters.ItemAdapter;
import com.example.cloket.models.ItemsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class itemsView extends AppCompatActivity
{
   ArrayList<ItemsModel> itemsArrayList;
   ItemAdapter itemAdapter;
   RecyclerView recyclerView;
   TextView itemNameTV, itemDescTV,dateAddedTV, categoryHeading;
   ImageView itemImageView;
   Button backBtn;

   static final String TAG = "ITEM_LIST_TAG";
    String categoryId, categoryTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_view);

        recyclerView = findViewById(R.id.recyclerView);
        itemImageView = findViewById(R.id.itemImageView);
        itemNameTV = findViewById(R.id.itemNameTV);
        itemDescTV = findViewById(R.id.itemDescTV);
        dateAddedTV = findViewById(R.id.dateAddedTV);
        categoryHeading = findViewById(R.id.categoryHeading);
        backBtn = findViewById(R.id.backBtn);

        //gets data from intent
        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        categoryTitle = intent.getStringExtra("categoryTitle");

        //set category heading
        categoryHeading.setText(categoryTitle);

        loadItems();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                backButton();
            }
        });
    }



    private void backButton()
    {
        startActivity(new Intent(itemsView.this, HomePage.class));
    }

    private void loadItems()
    {
        itemsArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Items");
        ref.orderByChild("categoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        itemsArrayList.clear();
                        for(DataSnapshot ds : snapshot.getChildren())
                        {
                            ItemsModel model = ds.getValue(ItemsModel.class);
                            //add to list
                            itemsArrayList.add(model);

                            Log.d(TAG,"onDataChange: " + model.getId() + " " + model.getName() );
                        }
                        itemAdapter = new ItemAdapter(itemsView.this, itemsArrayList);
                        recyclerView.setAdapter(itemAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });
    }
}



