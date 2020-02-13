package com.example.game.Controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.game.R;

public class HowToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        ViewPager slide = findViewById(R.id.slide);

        SliderAdapter sliderAdapter = new SliderAdapter(this);
        slide.setAdapter(sliderAdapter);
    }
}
