package com.example.lavacarapp.controller;

import android.database.Cursor;

import com.example.lavacarapp.dao.DaoUser;
import com.example.lavacarapp.model.UserDatabase;
import com.example.lavacarapp.model.UserInfo;

import java.util.ArrayList;

public class UserCtrl {

    private final DaoUser daoUser;

    public UserCtrl (UserDatabase database){
        daoUser = new DaoUser(database);
    }

    public long salvarUser(UserInfo user){
        return daoUser.createUserInDB(user);
    }

    public boolean checkUser(UserInfo user){
        return daoUser.checkUser(user.getEmail(), user.getPassword());
    }

    public int alterarUser(UserInfo user){
        return daoUser.updateUserInDB(user);
    }

    public UserInfo getUserInfo(String email){
        return daoUser.getUser(email);
    }

    public ArrayList<UserInfo> getUsers(){
        return daoUser.retrieveUsersFromDB();
    }

}
