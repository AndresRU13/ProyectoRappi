package com.unipiloto.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SingUp_Form extends AppCompatActivity {

    DataBaseSingUp myDB;
    EditText editName, editEmail, editPassword, editRepass;
    int selectGender, selectRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_form);
         myDB = new DataBaseSingUp(this);

         editName = (EditText) findViewById(R.id.editFullNombre);
        editEmail = (EditText) findViewById(R.id.editCorreo);
        editPassword = (EditText) findViewById(R.id.editContraseña);
        editRepass = (EditText) findViewById(R.id.editConfirmacionContraseña);
    }


    public void Registrar(View view) {
        Integer valueGender = new Integer(selectGender);
        Integer valueRol = new Integer(selectRol);

        if (validar() == true) {
            if (validarC() == true) {
                Usuario user = new Usuario();

                user.setNombre(editName.getText().toString());
                user.setEmail(editEmail.getText().toString());
                user.setPassword(editPassword.getText().toString());
                user.setGenero(valueGender);
                user.setRol(valueRol);

                boolean check = myDB.checkUser(editEmail.getText().toString());
                if (check == false) {
                    boolean isInserted = myDB.insertData(user);
                    if (isInserted)
                        Toast.makeText(SingUp_Form.this, "Datos Registrados con EXITO", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(SingUp_Form.this, "Datos NO registrados", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(SingUp_Form.this, "El Correo YA existe", Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(SingUp_Form.this, "Los campos de la CONTRASEÑA NO coinciden", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(SingUp_Form.this, "Faltan completar alguno campos", Toast.LENGTH_LONG).show();
        }
    }

    public void Regresar(View view) {
        Intent i = new Intent(SingUp_Form.this, Login_Form.class);
        startActivity(i);
    }

    public boolean validar(){
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String pass = editPassword.getText().toString();
        String repass = editRepass.getText().toString();
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || repass.isEmpty())
            return false;
        else
            return true;
    }

    public boolean validarC(){
        String pass = editPassword.getText().toString();
        String repass = editRepass.getText().toString();
        if (pass.equals(repass))
            return true;
        else
            return false;
    }

    public void radioClickedGender(View view) {
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioMale:
                if (check)
                    selectGender = 1;
                break;
            case R.id.radioFemale:
                if (check)
                    selectGender = 0;
                break;
        }
    }

    public void radioClickedRol(View view) {
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioClient:
                if (check)
                    selectRol = 1;
                break;
            case R.id.radioVendor:
                if (check)
                    selectRol = 0;
                break;
        }
    }
}