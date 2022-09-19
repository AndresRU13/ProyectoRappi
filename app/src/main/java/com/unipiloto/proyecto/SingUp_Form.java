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
    TextInputLayout editName, editEmail, editPassword;
    int selectGender, selectRol;
    Button singUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_form);
         myDB = new DataBaseSingUp(this);

         editName = (TextInputLayout) findViewById(R.id.editFullNombre);
        editEmail = (TextInputLayout) findViewById(R.id.editCorreo);
        editPassword = (TextInputLayout) findViewById(R.id.editContraseña);
        //selectGender = (RadioButton) findViewById(R.id.radioMale);
        //selectRol = (RadioButton) findViewById(R.id.radioClient);

        singUp = (Button) findViewById(R.id.btnAceptar);
    }

    public void Registrar(View view) {
        Integer valueGender = new Integer(selectGender);
        Integer valueRol = new Integer(selectRol);

        Usuario user = new Usuario();

        user.setNombre(editName.getEditText().toString());
        user.setEmail(editEmail.getEditText().toString());
        user.setPassword(editPassword.getEditText().toString());
        user.setGenero(valueGender);
        user.setRol(valueRol);

        boolean isInserted = myDB.insertData(user);

        if (isInserted)
            Toast.makeText(SingUp_Form.this, "Datos Insertados", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(SingUp_Form.this, "Datos NO Insertados", Toast.LENGTH_LONG).show();
    }

    public void Regresar(View view) {
        Intent i = new Intent(SingUp_Form.this, Login_Form.class);
        startActivity(i);
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