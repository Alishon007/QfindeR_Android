package com.example.qfinder.controller;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qfinder.R;
import com.example.qfinder.model.ManagerDB;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class RegistroPaciente extends AppCompatActivity {

    public TextInputEditText etNombre, etApellido, etDependencia, etFechaNacimiento, etSexo, etEdad;
    public Button btnVolver, btnContinuar;
    public ManagerDB managerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente);

        managerDB = new ManagerDB(this);
        managerDB.dbHelper.getWritableDatabase();


        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etDependencia = findViewById(R.id.etDependencia);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etSexo = findViewById(R.id.etSexo);
        etEdad = findViewById(R.id.etEdad);

        btnVolver = findViewById(R.id.btnVolver);
        btnContinuar = findViewById(R.id.btnContinuar);

        btnVolver.setOnClickListener(v -> finish());

        btnContinuar.setOnClickListener(v -> registrarPaciente());

        etFechaNacimiento.setOnClickListener(v -> mostrarSelectorFecha());
    }

    public void mostrarSelectorFecha() {
        final Calendar calendario = Calendar.getInstance();

        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog selectorFecha = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    // Formato visual dd/MM/yyyy
                    String fechaFormateada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    etFechaNacimiento.setText(fechaFormateada);
                },
                anio, mes, dia
        );

        selectorFecha.show();
    }

    public void registrarPaciente() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String dependencia = etDependencia.getText().toString().trim();
        String fechaNacimiento = etFechaNacimiento.getText().toString().trim();
        String sexo = etSexo.getText().toString().trim();
        String edadStr = etEdad.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || dependencia.isEmpty() ||
                fechaNacimiento.isEmpty() || sexo.isEmpty() || edadStr.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Edad inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        int idGenerado = (int) (Math.random() * 100000);
        managerDB.insertarPaciente(idGenerado, nombre, apellido, dependencia, fechaNacimiento, sexo, edad);
    }


}
