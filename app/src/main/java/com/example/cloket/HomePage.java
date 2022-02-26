package com.example.cloket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    //Initialize variables
    Button btnAddCategory, btnAddItem, btnHelp;
    FirebaseAuth firebaseAuth;
    ArrayList<ModelCategory> categoryArrayList =  new ArrayList<>();
    ModelCategory modelCategory = new ModelCategory();
    AdapterCategory adapterCategory;
    RecyclerView categoryRV , recyclerView;
    TextView CategoryTV, CategoryLimitET;


    ImageView btnMenu;

    static ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        btnHelp = findViewById(R.id.btnHelp);
        CategoryLimitET = findViewById(R.id.CategoryLimitET);
        categoryRV = findViewById(R.id.categoriesRV);
        CategoryTV = findViewById(R.id.CategoryTV);
        btnAddItem = findViewById(R.id.btnAddItems);
        categoryRV.setHasFixedSize(true);
        categoryRV.setLayoutManager(new LinearLayoutManager(this));
        btnAddCategory = findViewById(R.id.btnAddCategory);
        firebaseAuth =FirebaseAuth.getInstance();
        btnMenu = findViewById(R.id.bt_menu);
        DisplayCategories();


        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, HelpNdFeedback.class));
            }
        });
        btnAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the view log activity

                Intent intent = new Intent(HomePage.this, AddItems.class);
                startActivity(intent);

            }
        });

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, addCategory.class));
            }
        });

    }

    private void DisplayCategories()
    {
        categoryArrayList = new ArrayList<>();
        //gets all items under Categories from firebase
        DatabaseReference ref = FirebaseDatabase.
                getInstance().getReference("Categories");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                //clearing array list before adding data in it
                categoryArrayList.clear();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    //gets data from firebase
                    ModelCategory modelCate = ds.getValue(ModelCategory.class);

                    //add to array list
                    categoryArrayList.add(modelCate);
                }
                adapterCategory = new AdapterCategory(HomePage.this
                        ,categoryArrayList);

                categoryRV.setAdapter(adapterCategory);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }


}