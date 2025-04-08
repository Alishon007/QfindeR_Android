package com.example.qfinder.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qfinder.Menu;
import com.example.qfinder.R;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    TextView txtRegistrarme;
    Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        txtRegistrarme = findViewById(R.id.txtRegistrarme);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        txtRegistrarme.setOnClickListener(v -> {
            // Iniciamos la actividad de registro
            Intent intent = new Intent(Login.this, RegistroUsuario.class);
            startActivity(intent);
        });

        // Configuramos el listener para btnIniciarSesion
        btnIniciarSesion.setOnClickListener(v -> {
            // Iniciamos la actividad principal o siguiente
            Intent intent = new Intent(Login.this, Menu.class);
            startActivity(intent);
        });




    }
}