package com.example.lavacarapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavacarapp.R;
import com.example.lavacarapp.controller.UserCtrl;
import com.example.lavacarapp.model.UserDatabase;
import com.example.lavacarapp.model.UserInfo;

public class Profile extends AppCompatActivity {

    private EditText nomeUser, phoneUser, password, newPassword;
    private TextView emailUser;
    private Button updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nomeUser = findViewById(R.id.nome_user);
        phoneUser = findViewById(R.id.telefone_user);
        emailUser = findViewById(R.id.editTextTextEmailAddress3);
        password = findViewById(R.id.editTextTextPassword3);
        newPassword = findViewById(R.id.editTextTextPassword4);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        String userEmail = getIntent().getStringExtra("EMAIL");

        UserCtrl userCtrl = new UserCtrl(UserDatabase.getInstance(this));

        UserInfo loggedUser = userCtrl.getUserInfo(userEmail);

        if(loggedUser != null){
            nomeUser.setText(loggedUser.getNome());
            phoneUser.setText((loggedUser.getTelefone()));
            emailUser.setText(loggedUser.getEmail());
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
            builder.setMessage("Erro ao buscar dados na base.");
            builder.create().show();
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeCheck = emailUser.getText().toString().trim();
                String phoneCheck = phoneUser.getText().toString().trim();
                String passwordCheck = password.getText().toString().trim();
                String newPassword = newPassword.getBytes().toString();

                if(nomeCheck == loggedUser.getNome() && phoneCheck == loggedUser.getTelefone()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                    builder.setMessage("Dados iguais aos anteriores. Nada a ser atualizado.");
                    builder.create().show();
                }
                else if(passwordCheck != loggedUser.getPassword()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                    builder.setMessage("Senha atual incorreta.");
                    builder.create().show();
                }
                else if(passwordCheck == newPassword){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                    builder.setMessage("As duas senhas são iguais.");
                    builder.create().show();
                }
                else {
                    userCtrl.alterarUser(user);
                }
                
                Toast.makeText(getApplicationContext(), "Dados atualizados com sucesso!", Toast.LENGTH_LONG).show();
            }
        });



    }
}