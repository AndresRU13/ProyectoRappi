package com.unipiloto.proyecto.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.unipiloto.proyecto.Objects.Order;
import com.unipiloto.proyecto.Objects.Producto;
import com.unipiloto.proyecto.Objects.Usuario;

import java.util.ArrayList;
import java.util.List;

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
    private static final String PRO_8 = "IMAGE_ID";

    private static final String TABLE_ORDER = "Pedido_tabla";

    private static final String ORD_1 = "ID";
    private static final String ORD_2 = "NAME_USER";
    private static final String ORD_3 = "VALUE";
    private static final String ORD_4 = "WAY_TO_PAY";
    private static final String ORD_5 = "PROVIDER_NAME";
    private static final String ORD_6 = "LOCATION";
    private static final String ORD_7 = "DATE";

    public DataBaseRC(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USER + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE TEXT, EMAIL TEXT, PASSWORD TEXT, GENDER INTEGER, ROL INTEGER)");
        db.execSQL("create table " + TABLE_PRODUCT + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT, VALUE TEXT, EXPEDITION TEXT, LOCAL TEXT, CATEGORY INTEGER, IMAGE_ID INTEGER)");
        db.execSQL("create table " + TABLE_ORDER + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME_USER TEXT, VALUE TEXT, WAY_TO_PAY INTEGER, PROVIDER_NAME TEXT, LOCATION TEXT, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
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
        content.put(PRO_5, product.getExpedicion());
        content.put(PRO_6, product.getLocal());
        content.put(PRO_7, product.getCategoria());
        content.put(PRO_8, product.getImageResource());

        long res = db.insert(TABLE_PRODUCT, null, content);

        if (res == -1)
            return false;
        else
            return true;
    }

    public Cursor checkProd(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PRODUCT + " WHERE " + PRO_2 + " = ?", new String[] {name});
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

    public boolean insertOrder(Order order){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(ORD_2, order.getNameU());
        content.put(ORD_3, order.getValue());
        content.put(ORD_4, order.getPay());
        content.put(ORD_5, order.getNameV());
        content.put(ORD_6, order.getLocation());
        content.put(ORD_7, order.getDate());

        long res = db.insert(TABLE_ORDER, null, content);

        if (res == -1)
            return false;
        else
            return true;
    }

    public Cursor checkOrder(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ORDER + " WHERE " + PRO_2 + " = ?", new String[] {name});
    }

    public Cursor getAllOrders(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_ORDER, null);
        return res;
    }

    public Integer deleteOrder(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ORDER, " ID = ?", new String[] {id});
    }

    public List<Producto> listProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_ORDER, null);

        List<Producto> productoList = new ArrayList<>();
        if(res.moveToFirst()){
            do {
                productoList.add(new Producto(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7)));
            }while(res.moveToNext());
        }
        return productoList;
    }
}
