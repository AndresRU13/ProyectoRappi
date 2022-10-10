package com.unipiloto.proyecto.User_Forms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unipiloto.proyecto.DataBase.DataBaseRC;
import com.unipiloto.proyecto.Home.Home_Vendor;
import com.unipiloto.proyecto.R;

public class Login_Form extends AppCompatActivity {

    DataBaseRC myDB;
    EditText editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        myDB = new DataBaseRC(this);

        editEmail = (EditText) findViewById(R.id.logCorreo);
        editPassword = (EditText) findViewById(R.id.logContraseña);
    }

    public void Ingresar(View view) {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (validar() == true) {
            boolean checkuser = myDB.checkUser(email);
            if (checkuser == false) {
                boolean checkpass = myDB.checkPass(password);
                if (checkpass == false) {

                    Toast.makeText(Login_Form.this, "Bienvenido", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Login_Form.this, Home_Vendor.class);
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

    public void viewData(View view) {
        Cursor res = myDB.getAll();
        if(res.getCount() == 0){
            show("Error", "No encontrado");
            return;
        }

        StringBuffer buf = new StringBuffer();
        while(res.moveToNext()){
            buf.append("ID -> " + res.getString(0) + "\n");
            buf.append("Nombre -> " + res.getString(1) + "\n");
            buf.append("Correo -> " + res.getString(2) + "\n");
            buf.append("Contraseña -> " + res.getString(3) + "\n");
            buf.append("Genero -> " + res.getString(4) + "\n");
            buf.append("Rol -> " + res.getString(5) + "\n");
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