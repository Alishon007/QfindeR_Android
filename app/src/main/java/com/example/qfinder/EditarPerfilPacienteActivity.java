package com.example.qfinder; // cambia esto por tu paquete real

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.card.MaterialCardView; // Importar MaterialCardView
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditarPerfilPacienteActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtDependencia, txtFechaNacimiento, txtGenero, txtEdad;
    MaterialCardView btnGuardar;  // Cambiado a MaterialCardView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_paciente);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtDependencia = findViewById(R.id.txtDependencia);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento);
        txtGenero = findViewById(R.id.txtGenero);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuardar = findViewById(R.id.boton);  // btnGuardar ahora es MaterialCardView

        // Obtener los datos que vienen desde la vista anterior (PerfilPacienteActivity)
        Intent intent = getIntent();
        txtNombre.setText(intent.getStringExtra("nombre"));
        txtApellido.setText(intent.getStringExtra("apellido"));
        txtDependencia.setText(intent.getStringExtra("dependencia"));
        txtFechaNacimiento.setText(intent.getStringExtra("fechaNacimiento"));
        txtGenero.setText(intent.getStringExtra("genero"));
        txtEdad.setText(intent.getStringExtra("edad"));

        // Mostrar DatePicker al hacer clic en fecha
        txtFechaNacimiento.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(this,
                    (view, year1, month1, dayOfMonth) -> {
                        String fecha = String.format("%02d/%02d/%04d", dayOfMonth, month1 + 1, year1);
                        txtFechaNacimiento.setText(fecha);
                    },
                    year, month, day
            );
            datePicker.show();
        });

        // Guardar y regresar a la vista anterior
        btnGuardar.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("nombre", txtNombre.getText().toString());
            resultIntent.putExtra("apellido", txtApellido.getText().toString());
            resultIntent.putExtra("dependencia", txtDependencia.getText().toString());
            resultIntent.putExtra("fechaNacimiento", txtFechaNacimiento.getText().toString());
            resultIntent.putExtra("genero", txtGenero.getText().toString());
            resultIntent.putExtra("edad", txtEdad.getText().toString());

            setResult(RESULT_OK, resultIntent);
            finish(); // cerrar y volver al perfil
        });
    }
}
