package com.yagmur.runnablehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int number;
    Runnable runnable;
    Handler handler;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        start = findViewById(R.id.button);
    }

    public void start(View view){
        /* Locks the UI interface

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                number++;
                textView.setText("Time: " + number);
                handler.postDelayed(runnable, 1000);
            }
        };

        handler.post(runnable);
        start.setEnabled(false);
    }

    public void stop(View view){
        start.setEnabled(true);
        handler.removeCallbacks(runnable);
        number = 0;
        textView.setText("Time: " + number);
    }
}