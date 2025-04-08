package com.example.qfinder.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qfinder.R;
import com.example.qfinder.model.DbHelper; // ← Import correcto

import java.util.ArrayList;

public class ListaNotas extends AppCompatActivity {

    ImageButton btnagregarNota;
    ListView listaNotas;
    ArrayList<String> notas = new ArrayList<>();
    ArrayAdapter<String> adapter;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

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
