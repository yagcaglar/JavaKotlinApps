package com.yagmur.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            //delete app from phone/emulator to reset database
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);
            //id must be specified as INTEGER when initialing auto increment
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INT)");
            database.execSQL("INSERT INTO musicians (name, age) VALUES ('James', 50)");
            database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'James' ");
            database.execSQL("DELETE FROM musicians WHERE id = 2");
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'", null); //starting with K

            Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE age > 52", null);
            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");

            while(cursor.moveToNext()){
                System.out.println("Name: " + cursor.getString(nameIndex));
                System.out.println("Age: " + cursor.getInt(ageIndex));
                System.out.println("ID: "+ cursor.getInt(idIndex));
            }
            cursor.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}