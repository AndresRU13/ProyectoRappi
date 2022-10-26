package com.unipiloto.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.unipiloto.proyecto.DataBase.DataBaseRC;
import com.unipiloto.proyecto.Objects.Producto;

import java.util.ArrayList;

public class Create_Order extends AppCompatActivity {

    RecyclerView products;
    TextView totaltxt;
    DataBaseRC myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        totaltxt = (TextView) findViewById(R.id.total);
        products = (RecyclerView) findViewById(R.id.listProducts);

        products.setLayoutManager(new LinearLayoutManager(this));

        products.setAdapter();
    }


}