package com.yagmur.methodsandclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("on created called.");
        username = "James";
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on resume called.");
        testMethod();
        System.out.println(math(3,6));
        username = "Lars";
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("on stop called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("on pause called.");
    }

    public void testMethod(){
        int x = 4+4;
        System.out.println("value of x :" + x);
    }

    public int math(int a, int b){
        return a+b;
    }

    public void makeSimpsons(){
        Simpsons homer = new Simpsons("homer", 40, "teacher");
        homer.getAge();
        homer.setAge(45);
    }
    public void makeMusicians(){
        Musicians james = new Musicians("james", "guitar", 50);
    }
}