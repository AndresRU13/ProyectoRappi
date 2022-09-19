package com.unipiloto.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_Form extends AppCompatActivity {

    Button btn_create, btn_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

    }

    public void Ingresar(View view) {
    }

    public void Registrase(View view) {
        Intent i = new Intent(Login_Form.this, SingUp_Form.class);
        startActivity(i);
    }
}