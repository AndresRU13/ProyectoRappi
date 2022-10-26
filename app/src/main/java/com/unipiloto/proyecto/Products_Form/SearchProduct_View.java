package com.unipiloto.proyecto.Products_Form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.unipiloto.proyecto.DataBase.DataBaseRC;
import com.unipiloto.proyecto.Objects.Producto;
import com.unipiloto.proyecto.R;

public class SearchProduct_View extends AppCompatActivity {

    EditText name;
    DataBaseRC myDB;
    Producto pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product_view);

    }


    public void search(View view) {
    }
}