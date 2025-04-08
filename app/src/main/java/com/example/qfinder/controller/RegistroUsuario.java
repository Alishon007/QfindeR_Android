package com.example.qfinder.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qfinder.Menu;
import com.example.qfinder.R;

public class RegistroUsuario extends AppCompatActivity {
    Button btnVolver;
    Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        btnVolver = findViewById(R.id.btnVolver);
        btnContinuar = findViewById(R.id.btnContinuar);

        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(RegistroUsuario.this, Login.class);
            startActivity(intent);
        });
        btnContinuar.setOnClickListener(v -> {
            Intent intent = new Intent(RegistroUsuario.this, Menu.class);
            startActivity(intent);
        });

    }
}