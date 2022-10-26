package com.unipiloto.proyecto.Products_Form;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unipiloto.proyecto.DataBase.DataBaseRC;
import com.unipiloto.proyecto.Home.Home_Vendor;
import com.unipiloto.proyecto.R;

public class DeleteProduct_Form extends AppCompatActivity {

    DataBaseRC myDB;
    EditText idP;
    TextView products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteproduct_form);

        myDB = new DataBaseRC(this);

        idP = (EditText) findViewById(R.id.dIdProduct);
        products = (TextView) findViewById(R.id.products);

        Cursor res = myDB.getAllProduct();
        if(res.getCount() == 0){
            products.setText("No Existen Productos");
            return;
        }

        StringBuffer buf = new StringBuffer();
        while(res.moveToNext()){
            buf.append("Nombre -> " + res.getString(1) + "\n");
            buf.append("Descripcion -> " + res.getString(2) + "\n");
            buf.append("Valor -> $" + res.getString(3) + "\n");
            buf.append("Local -> " + res.getString(4) + "\n");
        }
        products.setText(buf);

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_Return:
                Intent intent = new Intent(this, Home_Vendor.class);
                startActivity(intent);
                return true;
            case R.id.action_AddProduct:
                Intent i = new Intent(this, AddProduct_Form.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteProdruct(View view) {
        String id = idP.getText().toString();
        Integer checkP = myDB.deleteProduct(id);
        if (checkP > 0) {
            Toast.makeText(DeleteProduct_Form.this, "Producto Eliminado", Toast.LENGTH_LONG).show();
            Cursor res = myDB.getAllProduct();
            if (res.getCount() == 0) {
                products.setText("No Existen Productos");
                return;
            }

            StringBuffer buf = new StringBuffer();
            while (res.moveToNext()) {
                buf.append("Nombre -> " + res.getString(1) + "\n");
                buf.append("Descripcion -> " + res.getString(2) + "\n");
                buf.append("Valor -> $" + res.getString(3) + "\n");
                buf.append("Local -> " + res.getString(4) + "\n");
            }
            products.setText(buf);
            idP.setText("");
        }else
            Toast.makeText(DeleteProduct_Form.this, "Producto NO Existe", Toast.LENGTH_LONG).show();

    }
}