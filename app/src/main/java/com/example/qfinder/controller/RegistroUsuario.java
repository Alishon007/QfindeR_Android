package com.example.qfinder.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qfinder.R;
import com.example.qfinder.model.ManagerDB;
import com.google.android.material.textfield.TextInputEditText;

public class RegistroUsuario extends AppCompatActivity {

    ManagerDB managerDB;

    TextInputEditText edtNombre, edtApellido, edtCorreo, edtTelefono, edtNacimiento, edtContrasena;
    Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        //Base de datos
        managerDB = new ManagerDB(RegistroUsuario.this);

        //Asiganacion de  elementos UI a variables de java
        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtNacimiento = findViewById(R.id.edtNacimiento);
        edtContrasena  = findViewById(R.id.edtContrasena);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(
                    edtNombre.length() < 0 &&
                            edtApellido.length() < 0 &&
                            edtCorreo.length() < 0 &&
                            edtTelefono.length() < 0 &&
                            edtNacimiento.length() < 0 &&
                            edtContrasena.length() < 0
                ){
                    Toast.makeText(RegistroUsuario.this, "Los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }else {
                    long result = managerDB.crearUsuario(
                            edtNombre.getText().toString(),
                            edtApellido.getText().toString(),
                            edtCorreo.getText().toString(),
                            edtTelefono.getText().toString(),
                            edtNacimiento.getText().toString(),
                            edtContrasena.getText().toString()
                    );

                    if(result < 0){
                        //Toast.makeText(MainActivity.this, "No se Inserto la ciudad: "+result, Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegistroUsuario.this, "Hubieron fallos en el registro del usuario", Toast.LENGTH_SHORT).show();
                    }else{
                        //Toast.makeText(MainActivity.this, "Se Inserto la ciudad con exito: "+result, Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegistroUsuario.this, "Se registro fue exitoso", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}