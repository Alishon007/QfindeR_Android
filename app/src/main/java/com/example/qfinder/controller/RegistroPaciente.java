package com.example.qfinder.controller;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qfinder.R;
import com.example.qfinder.model.ManagerDB;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class RegistroPaciente extends AppCompatActivity {

    private TextInputEditText etNombre, etApellido, etDependencia, etFechaNacimiento, etSexo, etEdad;
    private Button btnVolver, btnContinuar;
    private ManagerDB managerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente);

        // Inicialización sin cambios en la DB
        managerDB = new ManagerDB(this);
        managerDB.dbHelper.getWritableDatabase();

        // Vincular vistas
        vincularComponentes();

        // Configurar listeners
        configurarEventos();
    }

    private void vincularComponentes() {
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etDependencia = findViewById(R.id.etDependencia);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etSexo = findViewById(R.id.etSexo);
        etEdad = findViewById(R.id.etEdad);

        btnVolver = findViewById(R.id.btnVolver);
        btnContinuar = findViewById(R.id.btnContinuar);
    }

    private void configurarEventos() {
        btnVolver.setOnClickListener(v -> finish());

        btnContinuar.setOnClickListener(v -> {
            if (validarFormulario()) {
                registrarPaciente();
            }
        });

        etFechaNacimiento.setOnClickListener(v -> mostrarDatePicker());
    }

    private void mostrarDatePicker() {
        final Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String fechaFormateada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    etFechaNacimiento.setText(fechaFormateada);
                },
                anio, mes, dia
        );
        datePicker.show();
    }

    private boolean validarFormulario() {
        boolean valido = true;

        if (etNombre.getText().toString().trim().isEmpty()) {
            etNombre.setError("Campo obligatorio");
            valido = false;
        }

        if (etApellido.getText().toString().trim().isEmpty()) {
            etApellido.setError("Campo obligatorio");
            valido = false;
        }

        if (etDependencia.getText().toString().trim().isEmpty()) {
            etDependencia.setError("Campo obligatorio");
            valido = false;
        }

        if (etFechaNacimiento.getText().toString().trim().isEmpty()) {
            etFechaNacimiento.setError("Seleccione una fecha");
            valido = false;
        }

        if (etSexo.getText().toString().trim().isEmpty()) {
            etSexo.setError("Campo obligatorio");
            valido = false;
        }

        if (etEdad.getText().toString().trim().isEmpty()) {
            etEdad.setError("Campo obligatorio");
            valido = false;
        } else {
            try {
                int edad = Integer.parseInt(etEdad.getText().toString().trim());
                if (edad <= 0 || edad > 120) {
                    etEdad.setError("Edad inválida");
                    valido = false;
                }
            } catch (NumberFormatException e) {
                etEdad.setError("Debe ser un número");
                valido = false;
            }
        }

        return valido;
    }

    private void registrarPaciente() {
        try {
            // Obtener valores de los campos
            String nombre = etNombre.getText().toString().trim();
            String apellido = etApellido.getText().toString().trim();
            String dependencia = etDependencia.getText().toString().trim();
            String fechaNacimiento = etFechaNacimiento.getText().toString().trim();
            String sexo = etSexo.getText().toString().trim();
            int edad = Integer.parseInt(etEdad.getText().toString().trim());

            // Generar ID aleatorio (como en tu versión original)
            int idGenerado = (int) (Math.random() * 100000);

            // Insertar en la DB (sin cambios)
            managerDB.insertarPaciente(idGenerado, nombre, apellido, dependencia, fechaNacimiento, sexo, edad);

            // Mostrar confirmación
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

            // Limpiar formulario
            limpiarFormulario();

        } catch (Exception e) {
            Toast.makeText(this, "Error al registrar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarFormulario() {
        etNombre.setText("");
        etApellido.setText("");
        etDependencia.setText("");
        etFechaNacimiento.setText("");
        etSexo.setText("");
        etEdad.setText("");
        etNombre.requestFocus();
    }
}