package com.example.qfinder;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.qfinder.model.DbHelper;

import java.util.Calendar;

public class CrearNota extends Fragment {

    private EditText etFecha, etDescripcion;
    private Button btnGuardarNota;
    private DbHelper dbHelper;

    public CrearNota() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crear_nota, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etFecha = view.findViewById(R.id.etFecha);
        etDescripcion = view.findViewById(R.id.etDescripcion);
        btnGuardarNota = view.findViewById(R.id.btnGuardarNota);
        dbHelper = new DbHelper(requireContext());

        // Abrir el calendario al hacer clic en el campo de fecha
        etFecha.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            // Mostrar el DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view1, year1, month1, dayOfMonth1) -> {
                // Formatear la fecha como 'dd/MM/yyyy'
                String date = dayOfMonth1 + "/" + (month1 + 1) + "/" + year1;
                etFecha.setText(date);
            }, year, month, dayOfMonth);
            datePickerDialog.show();
        });

        btnGuardarNota.setOnClickListener(v -> {
            String fecha = etFecha.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();

            if (TextUtils.isEmpty(fecha) || TextUtils.isEmpty(descripcion)) {
                Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean exito = dbHelper.insertarNota(fecha, descripcion);
            if (exito) {
                Toast.makeText(requireContext(), "Nota guardada correctamente", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigateUp();
            } else {
                Toast.makeText(requireContext(), "Error al guardar la nota", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
