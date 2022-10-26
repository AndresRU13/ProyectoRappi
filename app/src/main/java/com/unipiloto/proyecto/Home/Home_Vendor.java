package com.unipiloto.proyecto.Home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.unipiloto.proyecto.Create_Order;
import com.unipiloto.proyecto.Products_Form.AddProduct_Form;
import com.unipiloto.proyecto.Products_Form.DeleteProduct_Form;
import com.unipiloto.proyecto.R;
import com.unipiloto.proyecto.Products_Form.SearchProduct_View;
import com.unipiloto.proyecto.User_Forms.Login_Form;

public class Home_Vendor extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_vendor);
        if (ActivityCompat.checkSelfPermission(Home_Vendor.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            Log.v("Solicitud de permisos", "mensaje");

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
            case R.id.action_AddProduct:
            Intent intent = new Intent(this, AddProduct_Form.class);
            startActivity(intent);
            return true;

            case R.id.action_delete:
                Intent i = new Intent(this, DeleteProduct_Form.class);
                startActivity(i);
                return true;

            case R.id.action_Return:
                Intent intent1 = new Intent(this, Login_Form.class);
                startActivity(intent1);
                return true;

            case R.id.action_location:
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("www.google.com")
                        .appendPath("maps")
                        .appendPath("dir")
                        .appendPath("")
                        .appendQueryParameter("api", "1")
                        .appendQueryParameter("origin", 4.6975074 + "," + -74.1122391);

                String url = builder.build().toString();
                Log.d("Direccion", url);
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse(url));
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void searchProdruct(View view) {
        Intent intent = new Intent(Home_Vendor.this, SearchProduct_View.class);
        startActivity(intent);
    }

    public void createOrder(View view) {
        Intent intent = new Intent(this, Create_Order.class);
        startActivity(intent);
    }
}