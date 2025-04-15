package com.example.qfinder.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.qfinder.Menu;
import com.example.qfinder.R;
import com.example.qfinder.Recordatorio;

public class PerfilPaciente extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu_icon;

    Button btnReconrdatorio;
    Button btRegistroPaciente;
    Button btnNotas;
    Button btnPerfilPaciente;
    Button btnPerfil, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_paciente);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu_icon = findViewById(R.id.menu_icon);

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        btnReconrdatorio = findViewById(R.id.btnRecordatorio);
        btRegistroPaciente = findViewById(R.id.btnRegitroPaciente);
        btnNotas = findViewById(R.id.btnNotas);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnPerfilPaciente = findViewById(R.id.btnPerfilPaciente);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilPaciente.this, Login.class);
            startActivity(intent);
        });



        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilPaciente.this, MainPerfil.class);
            startActivity(intent);
        });
        btnReconrdatorio.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilPaciente.this, Recordatorio.class);
            startActivity(intent);
        });
        btnNotas.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilPaciente.this, ListaNotas.class);
            startActivity(intent);
        });
        btRegistroPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilPaciente.this, RegistroPaciente.class);
            startActivity(intent);
        });
        btnPerfilPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilPaciente.this, PerfilPaciente.class);
            startActivity(intent);
        });

    }
}