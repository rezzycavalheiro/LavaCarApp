package com.example.lavacarapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "user.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "User";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "nome";
    private static final String COL_PHONE = "telefone";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";

    /*
    CREATE
    RETRIEVE
    UPDATE
    DELETE
    CRUD
     */

    private Context context;

    public UserDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query = String.format("CREATE TABLE IF NOT EXISTS %s("+
                " %s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " %s TEXT, " +
                " %s TEXT, " +
                " %s TEXT, " +
                " %s TEXT);",DB_TABLE,COL_ID,COL_NAME,COL_PHONE,COL_EMAIL,COL_PASSWORD);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query;
        query = String.format("DROP TABLE IF EXISTS " + DB_TABLE);
        db.execSQL(query);
        onCreate(db);
    }

    //CRUD
    //CREATE - CRIA UM USER NO BD
    public long createUserInDB(UserInfo user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,user.getNome());
        values.put(COL_PHONE,user.getTelefone());
        values.put(COL_EMAIL,user.getEmail());
        values.put(COL_PASSWORD,user.getPassword());
        long id = db.insert(DB_TABLE,null,values);
        db.insert(DB_TABLE,null,values);
        db.close();

        return id;
    }

    //RETRIEVE - TRAZER OS DADOS DO BD
    public ArrayList<UserInfo> retrieveUsersFromDB(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE,null,null,
                null,null,null,null);
        ArrayList<UserInfo> users = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
                String nome = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String telefone = cursor.getString(cursor.getColumnIndex(COL_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD));

                UserInfo c = new UserInfo(id,nome,telefone,email,password);
                users.add(c);

            } while (cursor.moveToNext());
        }
        db.close();
        return users;
    }

    // UPDATE - ALTERAR DADOS NO BD
    public int updateUserInDB(UserInfo user){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,user.getNome());
        values.put(COL_PHONE,user.getTelefone());
        values.put(COL_PASSWORD,user.getPassword());
        String id = String.valueOf(user.getId());
        int count = db.update(DB_TABLE, values, COL_ID + "=?", new String[]{id});
        db.close();
        return count;
    }

    //DELETE - DELETAR DADOS DO BD
    public void deleteUserInDB(UserInfo user){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DB_TABLE, COL_ID + "id =?", new String[] {String.valueOf(user.getId())});
        db.close();
    }

    // MÉTODOS PARA LOGIN

    // CHECAR SE USUÁRIO EXISTE
    public boolean checkEmail(String email) {
        String[] columns = {COL_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(DB_TABLE,columns,selection,
                selectionArgs,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    // CHECAR SE EXISTE USUÁRIO + EMAIL PARA LOGIN
    public boolean checkUser(String email, String password) {
        String[] columns = {COL_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_EMAIL + " = ?" + " AND " + COL_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(DB_TABLE, columns, selection,
                selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
