package com.example.qfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.qfinder.controller.ListaNotas;
import com.example.qfinder.controller.RegistroPaciente;

public class Menu extends AppCompatActivity {
    Button btnReconrdatorio;
    Button btRegistroPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnReconrdatorio = findViewById(R.id.btnRecordatorio);
        btRegistroPaciente = findViewById(R.id.btnRegitroPaciente);
        
        btnReconrdatorio.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, Recordatorio.class);
            startActivity(intent);
        });
        btRegistroPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, RegistroPaciente.class);
            startActivity(intent);
        });

    }
}