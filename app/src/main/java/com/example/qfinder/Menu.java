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
import com.example.qfinder.controller.RegistroUsuario;

public class Menu extends AppCompatActivity {
    Button btnNotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnNotas = findViewById(R.id.btnNotas);

        btnNotas.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, ListaNotas.class);
            startActivity(intent);
        });

    }
}