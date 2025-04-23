package com.example.qfinder.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qfinder.Menu;
import com.example.qfinder.R;
import com.example.qfinder.model.ManagerDB;

public class Login extends AppCompatActivity {

    TextView txtRegistrarme;
    Button btnIniciarSesion;
    EditText etEmail, etPassword;
    ManagerDB managerDB; // Instancia para gestionar la base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Vincular vistas
        txtRegistrarme = findViewById(R.id.txtRegistrarme);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        etEmail = findViewById(R.id.edtEmail); // Correo
        etPassword = findViewById(R.id.edtPassword); // Contraseña

        // Instancia de la base de datos
        managerDB = new ManagerDB(this);

        // Listener para "Registrarme"
        txtRegistrarme.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, RegistroUsuario.class);
            startActivity(intent);
        });

        // Listener para "Iniciar Sesión"
        btnIniciarSesion.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Validación de campos vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificación de credenciales
            boolean credencialesValidas = managerDB.verificarCredenciales(email, password);
            if (credencialesValidas) {
                // Credenciales correctas, redirigir al menú principal
                Intent intent = new Intent(Login.this, Menu.class);
                startActivity(intent);
                finish(); // Finaliza la actividad de Login para evitar volver con "atrás"
            } else {
                // Credenciales incorrectas
                Toast.makeText(Login.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}