package com.unipiloto.proyecto.Products_Form;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.unipiloto.proyecto.DataBase.DataBaseRC;
import com.unipiloto.proyecto.Home.Home_Vendor;
import com.unipiloto.proyecto.Objects.Producto;
import com.unipiloto.proyecto.R;

public class AddProduct_Form extends AppCompatActivity {

    DataBaseRC myDB;
    EditText editNombre, editDescripcion, editValor, editLocal;
    int selectCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct_form);

        myDB = new DataBaseRC(this);

        editNombre = (EditText) findViewById(R.id.editName);
        editDescripcion = (EditText) findViewById(R.id.editdescrip);
        editValor = (EditText) findViewById(R.id.editValue);
        editLocal = (EditText) findViewById(R.id.editLocal);

    }

    public void addProducto(View view) {
        Integer valueCategory = new Integer(selectCategory);

        if (validarCampos() == true) {
            Producto product = new Producto();

            product.setNombre(editNombre.getText().toString());
            product.setDescripcion(editDescripcion.getText().toString());
            product.setValor(editValor.getText().toString());
            product.setLocal(editLocal.getText().toString());

            String category = "Bebida";

            if (valueCategory == 0){
                category = "Comida";
            }

            product.setCategoria(category);

            boolean isInserted = myDB.insertProducto(product);
            if (isInserted) {
                Toast.makeText(AddProduct_Form.this, "Producto Agregado satisfactoriamente", Toast.LENGTH_LONG).show();
                editNombre.setText("");
                editDescripcion.setText("");
                editValor.setText("");
                editLocal.setText("");
            }else
                Toast.makeText(AddProduct_Form.this, "Producto NO Agregado", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(AddProduct_Form.this, "Falta diligenciar algunos Campos", Toast.LENGTH_LONG).show();
    }

    public void Regresar(View view) {
        Intent i = new Intent(AddProduct_Form.this, Home_Vendor.class);
        startActivity(i);
    }

    private boolean validarCampos() {
        String name = editNombre.getText().toString();
        String description = editDescripcion.getText().toString();
        String value = editValor.getText().toString();
        String local = editLocal.getText().toString();

        if (name.isEmpty() || description.isEmpty() || value.isEmpty() || local.isEmpty())
            return false;
        else
            return true;
    }

    public void radioClickedCategory(View view) {
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioDrink:
                if (check)
                    selectCategory = 1;
                break;
            case R.id.radioFood:
                if (check)
                    selectCategory = 0;
                break;
        }
    }

    public void viewProduct(View view) {
        Cursor res = myDB.getAllProduct();
        if(res.getCount() == 0){
            show("Error", "No encontrado");
            return;
        }

        StringBuffer buf = new StringBuffer();
        while(res.moveToNext()){
            buf.append("ID -> " + res.getString(0) + "\n");
            buf.append("Nombre -> " + res.getString(1) + "\n");
            buf.append("Descripcion -> " + res.getString(2) + "\n");
            buf.append("Valor -> $" + res.getString(3) + "\n");
            buf.append("Local -> " + res.getString(4) + "\n");
            buf.append("Categoria -> " + res.getString(5) + "\n");
        }
        show("Data", buf.toString());
    }

    public void show(String title, String message){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(title);
        build.setMessage(message);
        build.show();
    }
}