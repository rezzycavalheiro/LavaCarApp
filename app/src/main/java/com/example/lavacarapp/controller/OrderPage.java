package com.example.lavacarapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lavacarapp.R;

public class OrderPage extends AppCompatActivity {

    private TextView cleanLevelText;
    private ProgressBar cleanLevelProgress;
    private SeekBar cleanLevelSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        cleanLevelText = findViewById(R.id.text_clean_level);
        cleanLevelProgress = findViewById(R.id.progressBar_clean_level);
        cleanLevelSeekBar = findViewById(R.id.seekBar_clean_level);

        cleanLevelProgress.setMax(3);
        cleanLevelSeekBar.setMax(3);

        cleanLevelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cleanLevelProgress.setProgress(progress);
                cleanLevelText.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void openGalleryButton(View view){
        Intent intent = new Intent(this, CameraPage.class);
        startActivity(intent);
    }

    public void sendOrderButton(View view){
        Intent intent = new Intent(this, OrderSent.class);
        startActivity(intent);
    }
}