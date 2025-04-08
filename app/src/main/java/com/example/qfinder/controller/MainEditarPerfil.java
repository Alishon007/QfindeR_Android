package com.example.qfinder.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qfinder.R;

public class MainEditarPerfil extends AppCompatActivity {

    private EditText etNombre, etUsuario, etContacto, etEmail;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

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
