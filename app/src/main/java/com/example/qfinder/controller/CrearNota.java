package com.example.qfinder.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qfinder.R;
import com.example.qfinder.model.DbHelper;

public class CrearNota extends AppCompatActivity {

    EditText etFecha, etDescripcion;
    Button btnGuardar;
    DbHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);

        etFecha = findViewById(R.id.etFecha);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnGuardar = findViewById(R.id.btnGuardarNota);
        dbHelper = new DbHelper(this);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fecha = etFecha.getText().toString();
                String descripcion = etDescripcion.getText().toString();

                if (!fecha.isEmpty() && !descripcion.isEmpty()) {
                    boolean success = dbHelper.insertarNota(fecha, descripcion);
                    if (success) {
                        finish(); // Volver a la lista después de guardar
                    }
                }
            }
        });

    }
}