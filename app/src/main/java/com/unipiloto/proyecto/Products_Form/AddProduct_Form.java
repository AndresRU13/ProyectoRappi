package com.unipiloto.proyecto.Products_Form;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.unipiloto.proyecto.DataBase.DataBaseRC;
import com.unipiloto.proyecto.Home.Home_Vendor;
import com.unipiloto.proyecto.Objects.Producto;
import com.unipiloto.proyecto.R;

public class AddProduct_Form extends AppCompatActivity {

    DataBaseRC myDB;
    EditText editNombre, editDescripcion, editValor, editLocal, editExpedition;
    ImageView imagen;
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
        editExpedition = (EditText) findViewById(R.id.editExpedition);

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
            case R.id.action_delete:
                Intent i = new Intent(this, DeleteProduct_Form.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addProducto(View view) {
        Integer valueCategory = new Integer(selectCategory);

        if (validarCampos() == true) {
            Producto product = new Producto();

            if (imagen.getImageAlpha() == 255) {
                product.setImageResource(imagen.getImageAlpha());
                product.setNombre(editNombre.getText().toString());
                product.setDescripcion(editDescripcion.getText().toString());
                product.setValor(editValor.getText().toString());
                product.setLocal(editLocal.getText().toString());
                product.setExpedicion(editExpedition.getText().toString());

                String category = "Bebida";

                if (valueCategory == 0) {
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
                    editExpedition.setText("");

                } else
                    Toast.makeText(AddProduct_Form.this, "Producto NO Agregado", Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(AddProduct_Form.this, "NO has agregado Imagen del Producto", Toast.LENGTH_LONG).show();
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
        String expedition = editExpedition.getText().toString();

        if (name.isEmpty() || description.isEmpty() || value.isEmpty() || local.isEmpty() || expedition.isEmpty())
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
            //buf.append("Imagen: " + res.getNotificationUri() + "\n");
            buf.append("ID -> " + res.getString(0) + "\n");
            buf.append("Nombre -> " + res.getString(1) + "\n");
            buf.append("Descripcion -> " + res.getString(2) + "\n");
            buf.append("Valor -> $" + res.getString(3) + "\n");
            buf.append("Expedition -> " + res.getString(4) + "\n");
            buf.append("Local -> " + res.getString(5) + "\n");
            buf.append("Categoria -> " + res.getString(6) + "\n");
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

    public void ImageSelect(View view) {
        imagen = (ImageView) findViewById(R.id.imagen);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione La Aplicacion"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Uri path = data.getData();
            imagen.setImageURI(path);
        }
    }
}