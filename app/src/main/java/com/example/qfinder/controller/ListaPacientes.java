package com.example.qfinder.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qfinder.PerfilPacienteFragment;
import com.example.qfinder.R;
import com.example.qfinder.model.ManagerDB;

import java.util.ArrayList;

public class ListaPacientes extends AppCompatActivity {

    private ListView listView;
    private ManagerDB managerDB;
    private ArrayList<String> patientDisplayList;
    private ArrayList<Integer> patientIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        listView = findViewById(R.id.listView);
        managerDB = new ManagerDB(this);
        patientDisplayList = new ArrayList<>();
        patientIds = new ArrayList<>();

        // Llenar la lista de pacientes
        cargarListaPacientes();

        // Configurar adaptador para mostrar datos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patientDisplayList);
        listView.setAdapter(adapter);

        // Manejar clics en la lista
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent = new Intent(ListaPacientes.this, PerfilPacienteFragment.class);
            intent.putExtra("patient_id", patientIds.get(position)); // Pasar el ID del paciente
            startActivity(intent);
        });
    }

    private void cargarListaPacientes() {
        Cursor cursor = managerDB.obtenerPacientes();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombres"));
            String apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellidos"));
            String dependencia = cursor.getString(cursor.getColumnIndexOrThrow("dependencia"));

            patientDisplayList.add(nombre + " " + apellido + " - " + dependencia); // Nombre y dependencia
            patientIds.add(id); // Almacenar el ID para usarlo más tarde
        }
        cursor.close();
    }
}
