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
        setContentView(R.layout.activity_logged_page);
    }

    public void cameraButton(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}