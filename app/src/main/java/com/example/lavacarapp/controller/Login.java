package com.example.lavacarapp.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lavacarapp.R;
import com.example.lavacarapp.model.DataModel;
import com.example.lavacarapp.model.UserDatabase;

public class Login extends AppCompatActivity {

    private EditText editTextTextEmailAddress;
    private EditText editTextTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

    }

    public void onClickLogin(View view){ checkLogin(); }

    public void checkLogin() {
        UserDatabase db = new UserDatabase(Login.this);
        String userEmail = editTextTextEmailAddress.getText().toString().trim();
        String userPwd = editTextTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPwd)){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setMessage("E-mail ou senha vazios.");
            builder.create().show();
        }
        else if (db.checkUser(userEmail, userPwd)) {
            Intent loggedIn = new Intent(this, OrderPage.class);
            loggedIn.putExtra("EMAIL", editTextTextEmailAddress.getText().toString().trim());
            cleanInput();
            startActivity(loggedIn);
        }
        else {
//            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
//            builder.setMessage("E-mail ou senha incorretos.");
//            builder.create().show();

            Intent loggedIn = new Intent(this, OrderPage.class);
            loggedIn.putExtra("EMAIL", editTextTextEmailAddress.getText().toString().trim());
            cleanInput();
            startActivity(loggedIn);
        }
    }

    private void cleanInput() {
        editTextTextEmailAddress.setText("");
        editTextTextPassword.setText("");
    }
}