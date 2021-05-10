package com.example.lavacarapp.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class DataModel {
/***

 private static DataModel instance = new DataModel();
 private DataModel(){

 }
 public static DataModel getInstance(){
 return instance;
 }
 private UserDatabase db;
 private ArrayList<UserInfo> users;
 private Context context;

 public void setContext(Context context){
 this.context = context;
 db = new UserDatabase(context);
 users = db.retrieveUsersFromDB();
 }

 public ArrayList<UserInfo> getUsers(){
 return users;
 }

 public void addUser(UserInfo user){
 long id = db.createUserInDB(user);
 if(id > 0){
 user.setId((int) id);
 users.add(user);
 }else{
 Toast.makeText(
 context,"Problema ao criar usuário.",
 Toast.LENGTH_LONG).show();
 }
 }

 public void updateUser(UserInfo user, int position){
 int count = db.updateUserInDB(user);
 if(count > 0){
 users.set(position, user);
 }

 } **/
}

