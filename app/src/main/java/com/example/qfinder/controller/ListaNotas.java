package com.example.qfinder.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.qfinder.Menu;
import com.example.qfinder.R;
import com.example.qfinder.Recordatorio;
import com.example.qfinder.model.DbHelper; // ← Import correcto

import java.util.ArrayList;

public class ListaNotas extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu_icon;

    ImageButton btnagregarNota;
    ListView listaNotas;

    Button btnReconrdatorio;
    Button btRegistroPaciente;
    Button btnNotas;
    Button btnPerfilPaciente;
    Button btnPerfil;


    ArrayList<String> notas = new ArrayList<>();
    ArrayAdapter<String> adapter;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

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
            Intent intent = new Intent(ListaNotas.this, MainPerfil.class);
            startActivity(intent);
        });
        btnReconrdatorio.setOnClickListener(v -> {
            Intent intent = new Intent(ListaNotas.this, Recordatorio.class);
            startActivity(intent);
        });
        btnNotas.setOnClickListener(v -> {
            Intent intent = new Intent(ListaNotas.this, ListaNotas.class);
            startActivity(intent);
        });
        btRegistroPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(ListaNotas.this, RegistroPaciente.class);
            startActivity(intent);
        });
        btnPerfilPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(ListaNotas.this, PerfilPaciente.class);
            startActivity(intent);
        });

        listaNotas = findViewById(R.id.listaNotasss);
        dbHelper = new DbHelper(this); // ← Usa correctamente la clase
        btnagregarNota = findViewById(R.id.agregarNota);

        btnagregarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaNotas.this, CrearNota.class);
                startActivity(intent);
            }
        });

        cargarNotasDesdeBD();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notas);
        listaNotas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarNotasDesdeBD();
        adapter.notifyDataSetChanged();
    }

    private void cargarNotasDesdeBD() {
        notas.clear();
        Cursor cursor = dbHelper.obtenerNotas(); // ← Método sí existe en DbHelper
        if (cursor.moveToFirst()) {
            do {
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_FECHA));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_DESCRIPCION));
                notas.add(fecha + ": " + descripcion);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
