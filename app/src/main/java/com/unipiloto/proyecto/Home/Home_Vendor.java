package com.unipiloto.proyecto.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unipiloto.proyecto.Products_Form.DeleteProduct_Form;
import com.unipiloto.proyecto.Products_Form.AddProduct_Form;
import com.unipiloto.proyecto.R;

public class Home_Vendor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_vendor);

    }

    public void addProdruct(View view) {
        Intent intent = new Intent(Home_Vendor.this, AddProduct_Form.class);
        startActivity(intent);
    }

    public void deleteProdruct(View view) {
        Intent intent = new Intent(Home_Vendor.this, DeleteProduct_Form.class);
        startActivity(intent);
    }
}