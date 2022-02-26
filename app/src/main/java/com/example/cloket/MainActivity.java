package com.example.cloket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent mainPage = new Intent(MainActivity.this,Login.class);
                    startActivity(mainPage); //create navigation drawer page and call it MainPage
                    finish();
                }
            }
        };
        thread.start();
    }
}