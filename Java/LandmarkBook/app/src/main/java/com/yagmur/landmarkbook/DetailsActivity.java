package com.yagmur.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yagmur.landmarkbook.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();

        //listview
        Landmark selected = (Landmark) intent.getSerializableExtra("landmark");
        //Singleton singleton = Singleton.getInstance();
        //Landmark selected = singleton.getSentLandmark();
        binding.nameText.setText(selected.name);
        binding.countryText.setText(selected.country);
        binding.imageView.setImageResource(selected.image);
    }
}