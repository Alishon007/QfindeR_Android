package com.example.qfinder.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.qfinder.R;
import com.google.android.material.navigation.NavigationView;

public class MainPerfil extends AppCompatActivity {

    private ImageView imgPerfil;
    private TextView tvNombre, etUsuario, etContacto, etEmail;
    private Button btnEditar;

    DrawerLayout drawerLayout;
    ImageView menu_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu_icon = findViewById(R.id.menu_icon);

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Referencias a las vistas
        imgPerfil = findViewById(R.id.imgPerfil);
        tvNombre = findViewById(R.id.txtNombre);
        etUsuario = findViewById(R.id.txtUsuario);
        etContacto = findViewById(R.id.txtContacto);
        etEmail = findViewById(R.id.txtEmail);
        btnEditar = findViewById(R.id.btnEditar);

        // Obtener datos si vienen desde EditarPerfil
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tvNombre.setText(bundle.getString("nombre"));
            etUsuario.setText(bundle.getString("usuario"));
            etContacto.setText(bundle.getString("contacto"));
            etEmail.setText(bundle.getString("email"));
        }

        // Botón para ir a la pantalla de edición
        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(MainPerfil.this, MainEditarPerfil.class);
            intent.putExtra("nombre", tvNombre.getText().toString());
            intent.putExtra("usuario", etUsuario.getText().toString());
            intent.putExtra("contacto", etContacto.getText().toString());
            intent.putExtra("email", etEmail.getText().toString());
            startActivity(intent);
        });
    }
}
