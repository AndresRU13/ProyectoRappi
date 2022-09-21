package com.unipiloto.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login_Form extends AppCompatActivity {

    DataBaseSingUp myDB;
    EditText editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        myDB = new DataBaseSingUp(this);

        editEmail = (EditText) findViewById(R.id.logCorreo);
        editPassword = (EditText) findViewById(R.id.logContraseña);
    }

    public void Ingresar(View view) {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (validar() == true) {
            boolean checkuser = myDB.checkUser(email);
            if (checkuser == false) {
                boolean checkPass = myDB.checkPass(password);
                if (checkPass == false) {
                    Toast.makeText(Login_Form.this, "Bienvenido", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Login_Form.this, Home.class);
                    startActivity(i);
                } else
                    Toast.makeText(Login_Form.this, "La Contraseña es INCORRECTA", Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(Login_Form.this, "El usuario NO existe", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(Login_Form.this, "Falta Digitar tus datos", Toast.LENGTH_LONG).show();
    }

    public void Registrase(View view) {
        Intent i = new Intent(Login_Form.this, SingUp_Form.class);
        startActivity(i);
    }

    public boolean validar(){
        String email = editEmail.getText().toString();
        String pass = editPassword.getText().toString();

        if (email.isEmpty() || pass.isEmpty())
            return false;
        else
            return true;
    }
}