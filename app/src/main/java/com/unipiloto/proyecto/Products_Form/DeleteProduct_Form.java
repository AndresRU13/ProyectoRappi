package com.unipiloto.proyecto.Products_Form;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
            buf.append("ID -> " + res.getString(0) + "\n");
            buf.append("Nombre -> " + res.getString(1) + "\n");
            buf.append("Descripcion -> " + res.getString(2) + "\n");
            buf.append("Valor -> $" + res.getString(3) + "\n");
            buf.append("Local -> " + res.getString(4) + "\n");
        }
        show("Data", buf.toString());
    }

    public void deleteProdruct(View view) {
        String id = idP.getText().toString();
        Integer checkP = myDB.deleteProduct(id);
        if (checkP > 0)
            Toast.makeText(DeleteProduct_Form.this, "Producto Eliminado", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(DeleteProduct_Form.this, "Producto NO Existe", Toast.LENGTH_LONG).show();

    }

    public void show(String title, String message){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(title);
        build.setMessage(message);
        build.show();
    }

    public void Regresar(View view) {
        Intent i = new Intent(DeleteProduct_Form.this, Home_Vendor.class);
        startActivity(i);
    }
}