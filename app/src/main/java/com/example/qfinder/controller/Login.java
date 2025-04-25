package com.example.qfinder.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.qfinder.MenuQfinder;
import com.example.qfinder.R;
import com.example.qfinder.model.ManagerDB;
import com.example.qfinder.ui.perfil.PerfilFragment;  // Asegúrate de importar el fragmento de perfil

public class Login extends AppCompatActivity {

    TextView txtRegistrarme;
    Button btnIniciarSesion;
    EditText etEmail, etPassword;
    ManagerDB managerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtRegistrarme = findViewById(R.id.txtRegistrarme);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        etEmail = findViewById(R.id.edtEmail);
        etPassword = findViewById(R.id.edtPassword);

        managerDB = new ManagerDB(this);

        txtRegistrarme.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, RegistroUsuario.class);
            startActivity(intent);
        });

        btnIniciarSesion.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean credencialesValidas = managerDB.verificarCredenciales(email, password);
            if (credencialesValidas) {
                Cursor cursor = managerDB.obtenerDatosUsuario(email);
                if (cursor.moveToFirst()) {
                    // Recuperar datos
                    String nombres = cursor.getString(0);
                    String apellidos = cursor.getString(1);
                    String telefono = cursor.getString(3);

                    // Iniciar actividad MenuQfinder y enviar los datos
                    Intent intent = new Intent(Login.this, MenuQfinder.class);
                    intent.putExtra("nombre", nombres + " " + apellidos);
                    intent.putExtra("usuario", nombres);
                    intent.putExtra("contacto", telefono);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish(); // Opcional: cerrar login
                } else {
                    Toast.makeText(Login.this, "Error al obtener datos del usuario", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Login.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
