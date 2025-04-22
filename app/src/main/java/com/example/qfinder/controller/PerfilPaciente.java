package com.example.qfinder.controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.example.qfinder.model.ManagerDB;

public class PerfilPaciente extends AppCompatActivity {

    private TextView txtDependencia, txtNombres, txtFechaNacimiento, txtSexo, txtEdad;
    private ManagerDB managerDB;



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


        txtDependencia = findViewById(R.id.txtDependencia);
        txtNombres = findViewById(R.id.txtNombre);

        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento);
        txtSexo = findViewById(R.id.txtGenero);
        txtEdad = findViewById(R.id.txtEdad);

        managerDB = new ManagerDB(this);

        // Obtener ID del paciente del Intent
        int patientId = getIntent().getIntExtra("patient_id", -1);

        // Cargar detalles del paciente
        cargarDetallesPaciente(patientId);
    }

    private void cargarDetallesPaciente(int idPaciente) {
        SQLiteDatabase db = managerDB.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Paciente WHERE id = ?", new String[]{String.valueOf(idPaciente)});

        if (cursor.moveToFirst()) {
            String nombres = cursor.getString(cursor.getColumnIndexOrThrow("nombres"));
            String apellidos = cursor.getString(cursor.getColumnIndexOrThrow("apellidos"));

            String dependencia = cursor.getString(cursor.getColumnIndexOrThrow("dependencia"));
            String fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow("fechaNacimiento"));
            String sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo"));
            int edad = cursor.getInt(cursor.getColumnIndexOrThrow("edad"));

            // Mostrar los datos en los TextViews
            txtNombres.setText(nombres + " " + apellidos);

            txtDependencia.setText(dependencia);
            txtFechaNacimiento.setText(fechaNacimiento);
            txtSexo.setText(sexo);
            txtEdad.setText(String.valueOf(edad));
        }
        cursor.close();


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