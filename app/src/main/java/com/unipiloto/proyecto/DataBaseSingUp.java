package com.unipiloto.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseSingUp extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Usuarios.db";

    private static final String TABLE_NAME = "Registro_tabla";

    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";
    private static final String COL_5 = "GENDER";
    private static final String COL_6 = "ROL";

    Usuario user;

    public DataBaseSingUp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT, GENDER INTEGER, ROL INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Usuario user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(COL_2, user.getNombre());
        content.put(COL_3, user.getEmail());
        content.put(COL_4, user.getPassword());
        content.put(COL_5, user.getGenero());
        content.put(COL_6, user.getRol());

        long res = db.insert(TABLE_NAME, null, content);

        if (res == -1)
            return false;
        else
            return true;
    }

    public boolean checkUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_3 + " = ?", new String[] {email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public boolean checkPass(String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_4 + " = ?", new String[] {password});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }
}
