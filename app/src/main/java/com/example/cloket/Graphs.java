package com.example.cloket;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloket.adapters.MainAdapter;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class  Graphs extends AppCompatActivity {

    private Button click;
    private PieChart chart;
    private int i1 = 5;
    private int i2 = 3;
    private int i3 = 9;
    private int i4 = 5;

    //initialize variables
    DrawerLayout drawerLayout;
    ImageView btnMenu;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        click = findViewById(R.id.btn_click);
        chart = findViewById(R.id.pie_chart);

        //Assign variables
        drawerLayout = findViewById(R.id.drawer_layout);
        btnMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycler_view);

        //set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter
        recyclerView.setAdapter(new MainAdapter(this,HomePage.arrayList));
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open drawer

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToPieChart();
            }
        });

    }

    private void addToPieChart()
    {
        // add to pie chart

        chart.addPieSlice(new PieModel("Summer", i1, Color.parseColor("#C32727")));
        chart.addPieSlice(new PieModel("Autumn", i2, Color.parseColor("#FF5722")));
        chart.addPieSlice(new PieModel("Winter", i3, Color.parseColor("#00BCD4")));
        chart.addPieSlice(new PieModel("Spring", i4, Color.parseColor("#F130D0")));

        chart.startAnimation();
        click.setClickable(false);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //Close drawer
        HomePage.closeDrawer(drawerLayout);
    }
}
