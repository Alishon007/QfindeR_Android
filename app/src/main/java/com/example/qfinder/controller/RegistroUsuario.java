package com.example.qfinder.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        // Base de datos
        managerDB = new ManagerDB(RegistroUsuario.this);

        // Asignación de elementos UI a variables de Java
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
                // Validación de campos obligatorios
                if (edtNombre.getText().toString().isEmpty()) {
                    Toast.makeText(RegistroUsuario.this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
                    edtNombre.requestFocus();
                } else if (edtApellido.getText().toString().isEmpty()) {
                    Toast.makeText(RegistroUsuario.this, "El apellido es obligatorio", Toast.LENGTH_SHORT).show();
                    edtApellido.requestFocus();
                } else if (edtCorreo.getText().toString().isEmpty()) {
                    Toast.makeText(RegistroUsuario.this, "El correo es obligatorio", Toast.LENGTH_SHORT).show();
                    edtCorreo.requestFocus();
                } else if (edtTelefono.getText().toString().isEmpty()) {
                    Toast.makeText(RegistroUsuario.this, "El teléfono es obligatorio", Toast.LENGTH_SHORT).show();
                    edtTelefono.requestFocus();
                } else if (edtNacimiento.getText().toString().isEmpty()) {
                    Toast.makeText(RegistroUsuario.this, "La fecha de nacimiento es obligatoria", Toast.LENGTH_SHORT).show();
                    edtNacimiento.requestFocus();
                } else if (edtContrasena.getText().toString().isEmpty()) {
                    Toast.makeText(RegistroUsuario.this, "La contraseña es obligatoria", Toast.LENGTH_SHORT).show();
                    edtContrasena.requestFocus();
                } else {
                    // Insertar datos en la base de datos
                    long result = managerDB.crearUsuario(
                            edtNombre.getText().toString(),
                            edtApellido.getText().toString(),
                            edtCorreo.getText().toString(),
                            edtTelefono.getText().toString(),
                            edtNacimiento.getText().toString(),
                            edtContrasena.getText().toString()
                    );

                    if (result < 0) {
                        Toast.makeText(RegistroUsuario.this, "Hubieron fallos en el registro del usuario", Toast.LENGTH_SHORT).show();
                    } else {
                        // Guardar los datos en SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("nombre", edtNombre.getText().toString());
                        editor.putString("apellido", edtApellido.getText().toString());
                        editor.putString("correo", edtCorreo.getText().toString());
                        editor.putString("telefono", edtTelefono.getText().toString());
                        editor.putString("nacimiento", edtNacimiento.getText().toString());
                        editor.putString("contrasena", edtContrasena.getText().toString());
                        editor.apply();  // Guardar los datos en SharedPreferences

                        Toast.makeText(RegistroUsuario.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                        // Navegar al LoginActivity o cualquier otra actividad después del registro
                        Intent intent = new Intent(RegistroUsuario.this, Login.class);
                        startActivity(intent);
                        finish(); // Finalizar la actividad de registro
                    }
                }
            }
        });
    }
}
