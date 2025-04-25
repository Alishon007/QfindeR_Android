package com.example.qfinder;

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
        // Inflamos el layout del fragmento
        return inflater.inflate(R.layout.fragment_crear_nota, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etFecha = view.findViewById(R.id.etFecha);
        etDescripcion = view.findViewById(R.id.etDescripcion);
        btnGuardarNota = view.findViewById(R.id.btnGuardarNota);
        dbHelper = new DbHelper(requireContext());

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
                Navigation.findNavController(view).navigateUp(); // vuelve al fragmento anterior
            } else {
                Toast.makeText(requireContext(), "Error al guardar la nota", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
