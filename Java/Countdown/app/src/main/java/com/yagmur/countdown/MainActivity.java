package com.yagmur.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        new CountDownTimer(5000, 1000){
            @Override
            public void onTick(long l) {
                textView.setText("Left: " + l/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}