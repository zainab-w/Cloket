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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloket.adapters.AdapterCategory;
import com.example.cloket.adapters.MainAdapter;
import com.example.cloket.models.ModelCategory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    //Initialize variables
    Button btnAddCategory, btnAddItem;
    FirebaseAuth firebaseAuth;
    ArrayList<ModelCategory> categoryArrayList =  new ArrayList<>();
    ModelCategory modelCategory = new ModelCategory();
    AdapterCategory adapterCategory;
    RecyclerView categoryRV ;
    TextView CategoryTV, CategoryLimitET, amount;
    int count ;


    //nav drawer
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    MainAdapter adapter;
    ImageView btnMenu;

    static ArrayList<String> arrayList = new ArrayList<>();
    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        //check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            //if drawer is open then closes it
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //nav drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        btnMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycler_view);



        //amount = findViewById(R.id.amount);
        CategoryLimitET = findViewById(R.id.CategoryLimitET);
        categoryRV = findViewById(R.id.categoriesRV);
        CategoryTV = findViewById(R.id.CategoryTV);
        btnAddItem = findViewById(R.id.btnAddItems);
        categoryRV.setHasFixedSize(true);
        categoryRV.setLayoutManager(new LinearLayoutManager(this));
        btnAddCategory = findViewById(R.id.btnAddCategory);
        firebaseAuth =FirebaseAuth.getInstance();


        DisplayCategories();
//        ItemCount();
        //clear array list
        arrayList.clear();

        //add menu items into array list
        arrayList.add("HomePage");
        arrayList.add("Graphs");
        arrayList.add("HelpNdFeedback");
        arrayList.add("Logout");

        //Initialize adapter
        adapter = new MainAdapter(this,arrayList);
        //set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //set adapter
        recyclerView.setAdapter(adapter);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }

        });



        btnAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                AddItemsPage();
            }
        });

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                addCategoryPage();
            }
        });

    }

    private void ItemCount()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = rootRef.child("Items");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count = (int) dataSnapshot.getChildrenCount();
                Log.d("TAG", "count= " + count);
                amount.setText("" + Integer.toString(count));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref.addListenerForSingleValueEvent(valueEventListener);
    }



    @Override
    protected void onPause() {
        //close drawer
        super.onPause();
        closeDrawer(drawerLayout);
    }


    private void addCategoryPage()
    {
        startActivity(new Intent(HomePage.this, addCategory.class));

    }


    private void AddItemsPage()
    {
        startActivity(new Intent(HomePage.this, AddItems.class));
    }

    private void DisplayCategories()
    {

        categoryArrayList = new ArrayList<>();
        //int text = categoryArrayList.size() ;
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

//                amount.setText(text);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }


}