package com.example.qfinder.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.qfinder.R;
import com.example.qfinder.Recordatorio;

public class MainEditarPerfil extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu_icon;
    Button btnReconrdatorio;
    Button btRegistroPaciente;
    Button btnNotas;
    Button btnPerfilPaciente;
    Button btnPerfil;

    private EditText etNombre, etUsuario, etContacto, etEmail;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);


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



        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MainEditarPerfil.this, MainPerfil.class);
            startActivity(intent);
        });
        btnReconrdatorio.setOnClickListener(v -> {
            Intent intent = new Intent(MainEditarPerfil.this, Recordatorio.class);
            startActivity(intent);
        });
        btnNotas.setOnClickListener(v -> {
            Intent intent = new Intent(MainEditarPerfil.this, ListaNotas.class);
            startActivity(intent);
        });
        btRegistroPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(MainEditarPerfil.this, RegistroPaciente.class);
            startActivity(intent);
        });
        btnPerfilPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(MainEditarPerfil.this, PerfilPaciente.class);
            startActivity(intent);
        });

        // Referenciar campos
        etNombre = findViewById(R.id.etNombre);
        etUsuario = findViewById(R.id.etUsuario);
        etContacto = findViewById(R.id.etContacto);
        etEmail = findViewById(R.id.etEmail);
        btnGuardar = findViewById(R.id.btnGuardar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            etNombre.setText(bundle.getString("nombre", ""));
            etUsuario.setText(bundle.getString("usuario", ""));
            etContacto.setText(bundle.getString("contacto", ""));
            etEmail.setText(bundle.getString("email", ""));
        }

        btnGuardar.setOnClickListener(view -> {
            Intent intent = new Intent(MainEditarPerfil.this, MainPerfil.class);
            intent.putExtra("nombre", etNombre.getText().toString());
            intent.putExtra("usuario", etUsuario.getText().toString());
            intent.putExtra("contacto", etContacto.getText().toString());
            intent.putExtra("email", etEmail.getText().toString());
            startActivity(intent);
            finish(); // Cierra esta pantalla para no volver atrás innecesariamente
        });
    }
}
