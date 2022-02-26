package com.example.cloket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloket.adapters.MainAdapter;

public class HelpNdFeedback extends AppCompatActivity {

    //initialize variables
    DrawerLayout drawerLayout;
    ImageView btnMenu;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_nd_feedback);

        //Assign variables
        drawerLayout = findViewById(R.id.drawer_layout);
        btnMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycler_view);
        final EditText txtName = (EditText) findViewById(R.id.txtName);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        final EditText txtComment = (EditText) findViewById(R.id.txtComment);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        Button btnBack = (Button) findViewById(R.id.btnBack);


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
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/htm");
                i.putExtra(Intent.EXTRA_EMAIL, new String("xyz@gmail.com"));
                i.putExtra(Intent.EXTRA_SUBJECT, "Feedback from app");
                i.putExtra(Intent.EXTRA_TEXT, "Name:" + txtEmail.getText() + "\n Message:"
                        + txtComment.getText());
                try {
                    startActivity(Intent.createChooser(i, "please select email"));

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(HelpNdFeedback.this, "There are no clients",
                            Toast.LENGTH_SHORT);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HelpNdFeedback.this, HomePage.class));
            }
        });
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        //Close drawer
        HomePage.closeDrawer(drawerLayout);
    }
}