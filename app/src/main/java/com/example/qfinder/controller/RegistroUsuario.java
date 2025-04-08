package com.example.qfinder.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qfinder.Menu;
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

        // Inicialización de la base de datos
        managerDB = new ManagerDB(RegistroUsuario.this);

        // Asignación de elementos UI a variables Java
        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtNacimiento = findViewById(R.id.edtNacimiento);
        edtContrasena = findViewById(R.id.edtContrasena);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (
                        edtNombre.length() <= 0 ||
                                edtApellido.length() <= 0 ||
                                edtCorreo.length() <= 0 ||
                                edtTelefono.length() <= 0 ||
                                edtNacimiento.length() <= 0 ||
                                edtContrasena.length() <= 0
                ) {
                    Toast.makeText(RegistroUsuario.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    long result = managerDB.crearUsuario(
                            edtNombre.getText().toString(),
                            edtApellido.getText().toString(),
                            edtCorreo.getText().toString(),
                            edtTelefono.getText().toString(),
                            edtNacimiento.getText().toString(),
                            edtContrasena.getText().toString()
                    );

                    if (result < 0) {
                        Toast.makeText(RegistroUsuario.this, "Hubo un error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistroUsuario.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistroUsuario.this, Menu.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
