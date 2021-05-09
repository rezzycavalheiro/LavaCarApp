package com.example.lavacarapp.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lavacarapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class CameraPage extends AppCompatActivity {

    static final int CAMERA_PERMISSION_CODE = 2001;
    static final int CAMERA_INTENT_CODE = 3001;
    ImageView imageViewCamera;
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_page);
        imageViewCamera = findViewById(R.id.imageViewCamera);
    }

    public void cameraButton(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestCameraPermission();
        }
        else {
            sendCameraIntent();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void requestCameraPermission(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
               requestPermissions(new String[]{
                       Manifest.permission.CAMERA
               }, CAMERA_PERMISSION_CODE);
            }
            else {
                sendCameraIntent();
            }
        }
        else {
            Toast.makeText(CameraPage.this, "Não há câmeras disponíves.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendCameraIntent();
            }
            else {
                Toast.makeText(CameraPage.this, "Permissão de uso da câmera negada.", Toast.LENGTH_LONG).show();
            }
        }
    }

    void sendCameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
        if(intent.resolveActivity(getPackageManager()) != null){
            // new Date() retorna a hora atual
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date());
            String picName = "pic_" +  timeStamp;
            File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File pictureFile = null;
            try {
                pictureFile = File.createTempFile(picName, ".jpg",dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(pictureFile != null){
                picturePath = pictureFile.getAbsolutePath();
                Uri photouri = FileProvider.getUriForFile(
                        CameraPage.this,
                        "com.example.lavacarapp.fileprovider",
                        pictureFile
                );
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photouri);
                startActivityForResult(intent, CAMERA_INTENT_CODE);
//                galleryAddPic();
            }
        }
    }
    
    public void galleryAddPic(String file) {
        File f = new File(file);
        Uri contentUri = Uri.fromFile(f);
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri);
        sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_INTENT_CODE){
            if(resultCode == RESULT_OK){
                File file = new File(picturePath);
                if(file.exists()){
                    imageViewCamera.setImageURI(Uri.fromFile(file));
                    galleryAddPic(picturePath);
                }
                galleryAddPic(picturePath);
            }
            else {
                Toast.makeText(CameraPage.this, "Problema ao pegar a imagem da câmera.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void orderPageButton(View view){
        Intent orderPage = new Intent(this, OrderPage.class);
        startActivity(orderPage);
    }
}