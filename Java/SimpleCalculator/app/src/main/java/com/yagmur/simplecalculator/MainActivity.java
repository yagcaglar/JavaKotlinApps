package com.yagmur.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText num1text;
    EditText num2text;
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1text = findViewById(R.id.num1text);
        num2text = findViewById(R.id.num2text);
        resultText = findViewById(R.id.resultText);
    }

    public void sum(View view){
        if(num1text.getText().toString().matches("") || num2text.getText().toString().matches("")) {
            int num1 = Integer.parseInt(num1text.getText().toString());
            int num2 = Integer.parseInt(num2text.getText().toString());
            int result = num1 + num2;
            resultText.setText("Result: " + result);
            System.out.println(result);
        }
    }
    public void sub(View view){
        if(num1text.getText().toString().matches("") || num2text.getText().toString().matches("")) {
            int num1 = Integer.parseInt(num1text.getText().toString());
            int num2 = Integer.parseInt(num2text.getText().toString());
            int result = num1 - num2;
            resultText.setText("Result: " + result);
            System.out.println(result);
        }
    }

    public void mul(View view){
        if(num1text.getText().toString().matches("") || num2text.getText().toString().matches("")) {
            int num1 = Integer.parseInt(num1text.getText().toString());
            int num2 = Integer.parseInt(num2text.getText().toString());
            int result = num1 * num2;
            resultText.setText("Result: " + result);
            System.out.println(result);
        }
    }

    public void div(View view){
        if(num1text.getText().toString().matches("") || num2text.getText().toString().matches("")) {
            int num1 = Integer.parseInt(num1text.getText().toString());
            int num2 = Integer.parseInt(num2text.getText().toString());
            int result = num1 / num2;
            resultText.setText("Result: " + result);
            System.out.println(result);
        }
    }


}