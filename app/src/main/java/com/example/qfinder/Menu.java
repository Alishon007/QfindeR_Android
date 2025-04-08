package com.example.qfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qfinder.controller.ListaNotas;
import com.example.qfinder.controller.Login;
import com.example.qfinder.controller.RegistroPaciente;
import com.example.qfinder.controller.RegistroUsuario;

public class Menu extends AppCompatActivity {
    Button btnNotas;
    Button btRegistroPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnNotas = findViewById(R.id.btnNotas);
        btRegistroPaciente = findViewById(R.id.btnRegitroPaciente);
        
        btnNotas.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, ListaNotas.class);
            startActivity(intent);
        });
        btRegistroPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, RegistroPaciente.class);
            startActivity(intent);
        });

    }
}