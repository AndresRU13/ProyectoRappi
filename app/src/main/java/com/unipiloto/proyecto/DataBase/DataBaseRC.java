package com.unipiloto.proyecto.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.unipiloto.proyecto.Objects.Producto;
import com.unipiloto.proyecto.Objects.Usuario;

public class DataBaseRC extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Usuarios.db";

    private static final String TABLE_USER = "Registro_tabla";

    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "AGE";
    private static final String COL_4 = "EMAIL";
    private static final String COL_5 = "PASSWORD";
    private static final String COL_6 = "GENDER";
    private static final String COL_7 = "ROL";

    private static final String TABLE_PRODUCT = "Productos_tabla";

    private static final String PRO_1 = "ID";
    private static final String PRO_2 = "NAME";
    private static final String PRO_3 = "DESCRIPTION";
    private static final String PRO_4 = "VALUE";
    private static final String PRO_5 = "EXPEDITION";
    private static final String PRO_6 = "LOCAL";
    private static final String PRO_7 = "CATEGORY";

    public DataBaseRC(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USER + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE TEXT, EMAIL TEXT, PASSWORD TEXT, GENDER INTEGER, ROL INTEGER)");
        db.execSQL("create table " + TABLE_PRODUCT + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT, VALUE TEXT, EXPEDITION DATE, LOCAL TEXT, CATEGORY INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
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

        long res = db.insert(TABLE_USER, null, content);

        if (res == -1)
            return false;
        else
            return true;
    }

    public boolean checkUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_3 + " = ?", new String[] {email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public boolean checkPass(String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_4 + " = ?", new String[] {password});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        return res;
    }

    public Integer deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USER, " ID = ?", new String[] {id});
    }

    public boolean checkRol(String email, String rol){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_3 +" FROM " + TABLE_USER + " WHERE " + COL_6 + " = ?", new String[] {rol});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public boolean insertProducto(Producto product){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(PRO_2, product.getNombre());
        content.put(PRO_3, product.getDescripcion());
        content.put(PRO_4, product.getValor());
        content.put(PRO_5, product.getLocal());
        content.put(PRO_6, product.getCategoria());

        long res = db.insert(TABLE_PRODUCT, null, content);

        if (res == -1)
            return false;
        else
            return true;
    }

    public boolean checkProd(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT + " WHERE " + PRO_2 + " = ?", new String[] {name});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public Cursor getAllProduct(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT, null);
        return res;
    }

    public Integer deleteProduct(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PRODUCT, " ID = ?", new String[] {id});
    }
}
