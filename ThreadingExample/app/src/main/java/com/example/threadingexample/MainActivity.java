package com.example.threadingexample;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
TextView tvOutput;
private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.tvOutput);

    }

    public void buttonClicked(View view)
    {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run()
            {
                Toast.makeText(MainActivity.this, "Hi from the runnable", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Thread 2 " + Thread.currentThread().getName());
                Log.i(TAG, "Runnable ran");

                synchronized (this)
                {
                    try
                    {
                      wait(5000);
                      Log.i(TAG,"5 Seconds is up");
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                handler.post(new Runnable()
                {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Hi from the runnable", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "Toast ran");
                        tvOutput.setText("Toast ran after 5 seconds");
                    }
                });

                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Hi from the runnable", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "Toast ran after 5 seconds");
                        tvOutput.setText("Toast ran after 5 seconds");
                    }
                }, 5000);


            }
        };
        Log.i(TAG, "Thread 1 " + Thread.currentThread().getName());
        Log.i(TAG, "Button has been pressed");
        //runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}