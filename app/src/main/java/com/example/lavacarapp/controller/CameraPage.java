package com.example.lavacarapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lavacarapp.R;

public class CameraPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_page);
    }

    public void cameraButton(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void orderPageButton(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }
}