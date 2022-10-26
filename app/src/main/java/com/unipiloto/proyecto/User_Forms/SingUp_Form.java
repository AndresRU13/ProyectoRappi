package com.unipiloto.proyecto.User_Forms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.unipiloto.proyecto.DataBase.DataBaseRC;
import com.unipiloto.proyecto.Home.Home_Vendor;
import com.unipiloto.proyecto.Objects.Usuario;
import com.unipiloto.proyecto.R;

public class SingUp_Form extends AppCompatActivity {

    DataBaseRC myDB;
    EditText editName, editAge, editEmail, editPassword, editRepass;
    int selectGender, selectRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_form);
         myDB = new DataBaseRC(this);

         editName = (EditText) findViewById(R.id.editFullNombre);
        editAge = (EditText) findViewById(R.id.editAge);
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
                user.setEdad(editAge.getText().toString());
                user.setEmail(editEmail.getText().toString());
                user.setPassword(editPassword.getText().toString());

                String gender = "Hombre";
                String rol = "Cliente";

                if (valueGender == 0) {
                    gender = "mujer";
                }
                if (valueRol == 0) {
                    rol = "Vendedor";
                } else if (valueRol == 2) {
                    rol = "Repartidor";
                }

                user.setGenero(gender);
                user.setRol(rol);

                    if (validarE() == true) {
                        String email = editEmail.getText().toString();
                        boolean check = myDB.checkUser(email);
                        if (check == true) {
                            boolean isInserted = myDB.insertData(user);
                            if (isInserted) {
                                Toast.makeText(SingUp_Form.this, "Datos Registrados con EXITO", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(SingUp_Form.this, Home_Vendor.class);
                                startActivity(i);
                            } else
                                Toast.makeText(SingUp_Form.this, "Datos NO registrados", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(SingUp_Form.this, "El Correo YA existe", Toast.LENGTH_LONG).show();
                    } else if (validarE() == false && rol == "Vendedor" || rol == "Repartidor")
                        Toast.makeText(SingUp_Form.this, "NO eres mayor de edad", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(SingUp_Form.this, "Los campos de la CONTRASEÑA NO coinciden", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(SingUp_Form.this, "Faltan completar alguno campos", Toast.LENGTH_LONG).show();
            }
    }

    public void Regresar(View view) {
        Intent i = new Intent(SingUp_Form.this, Login_Form.class);
        startActivity(i);
    }

    public boolean validar(){
        String name = editName.getText().toString();
        String age = editAge.getText().toString();
        String email = editEmail.getText().toString();
        String pass = editPassword.getText().toString();
        String repass = editRepass.getText().toString();
        if (name.isEmpty() || age.isEmpty() || email.isEmpty() || pass.isEmpty() || repass.isEmpty())
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

    public boolean validarE(){
        String age = editAge.getText().toString();
        int ageI = Integer.parseInt(age);
        if (ageI >= 18)
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
            case R.id.radioRepart:
                if (check)
                    selectRol = 2;
                break;
        }
    }
}