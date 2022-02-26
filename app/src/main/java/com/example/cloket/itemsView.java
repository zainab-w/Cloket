package com.example.cloket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class itemsView extends AppCompatActivity
{
    private  RecyclerView recyclerView;
    ItemAdapter adapter;
    DatabaseReference mbase;
    FirebaseAuth firebaseAuth;
    Button addItemsBtn;

    List<ItemsModel> itemList = new ArrayList<>();
    ItemListAdapter itemListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_view);

        firebaseAuth = FirebaseAuth.getInstance();
        addItemsBtn = findViewById(R.id.addItemsBtn);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        mbase = FirebaseDatabase.getInstance().getReference("Items");
        //to call on array
        itemListAdapter = new ItemListAdapter(itemList);
        FirebaseRecyclerOptions<ItemsModel> options
                = new FirebaseRecyclerOptions.Builder<ItemsModel>()
                .setQuery(mbase, ItemsModel.class)
                .build();
        adapter = new ItemAdapter(options);
        recyclerView.setAdapter(adapter);


        addItemsBtn.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View v)
          {
              startActivity(new Intent(itemsView.this, AddItems.class));
          }
        });

    }

        @Override protected void onStart()
        {
            super.onStart();
            adapter.startListening();
        }
        @Override protected void onStop()
        {
            super.onStop();
            adapter.stopListening();
        }
    }



