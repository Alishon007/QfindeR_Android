package com.example.qfinder;

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

import com.example.qfinder.controller.ListaNotas;
import com.example.qfinder.controller.MainPerfil;
import com.example.qfinder.controller.PerfilPaciente;
import com.example.qfinder.controller.RegistroPaciente;

public class EditarPerfil extends AppCompatActivity {

    Button btnReconrdatorio;
    Button btRegistroPaciente;
    Button btnNotas;
    Button btnPerfilPaciente;
    Button btnPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);

        btnReconrdatorio = findViewById(R.id.btnRecordatorio);
        btRegistroPaciente = findViewById(R.id.btnRegitroPaciente);
        btnNotas = findViewById(R.id.btnNotas);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnPerfilPaciente = findViewById(R.id.btnPerfilPaciente);



        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(EditarPerfil.this, MainPerfil.class);
            startActivity(intent);
        });
        btnReconrdatorio.setOnClickListener(v -> {
            Intent intent = new Intent(EditarPerfil.this, Recordatorio.class);
            startActivity(intent);
        });
        btnNotas.setOnClickListener(v -> {
            Intent intent = new Intent(EditarPerfil.this, ListaNotas.class);
            startActivity(intent);
        });
        btRegistroPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(EditarPerfil.this, RegistroPaciente.class);
            startActivity(intent);
        });
        btnPerfilPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(EditarPerfil.this, PerfilPaciente.class);
            startActivity(intent);
        });

    }
}