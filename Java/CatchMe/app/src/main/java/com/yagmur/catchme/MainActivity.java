package com.yagmur.catchme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int score = 0;
    Handler handler;
    Runnable r;
    ImageView image;
    TextView time;
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageView);
        time = findViewById(R.id.textView);
        scoreText = findViewById(R.id.score);



        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                time.setText("Time: " + l/1000);
            }

            @Override
            public void onFinish() {
                time.setText("Finished!");
                handler.removeCallbacks(r);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over.", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();

        randomizeImage();
    }

    public void randomizeImage(){
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                int x = new Random().nextInt(600-100) + 100;
                int y = new Random().nextInt(1000-160) + 160;
                handler.postDelayed(r, 400);
                image.setX(x);
                image.setY(y);
            }
        };
        handler.post(r);
    }

    public void score(View view){
        score++;
        scoreText.setText("Score: " + score);
    }
}