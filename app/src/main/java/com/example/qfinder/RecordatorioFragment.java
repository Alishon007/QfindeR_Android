package com.example.qfinder;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.qfinder.model.ManagerDB;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecordatorioFragment extends Fragment {

    private ManagerDB managerDB;
    private Button btnSeleccionarFecha;
    private ListView listaDeRecordatorios;
    private TextInputEditText edtNombre;

    private List<String> listaRecordatorios;
    private ArrayAdapter<String> adaptador;

    public RecordatorioFragment() {
        // Constructor vacío requerido
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managerDB = new ManagerDB(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recordatorio, container, false);

        btnSeleccionarFecha = rootView.findViewById(R.id.btnSeleccionarFecha);
        listaDeRecordatorios = rootView.findViewById(R.id.listaDeRecordatorios);
        edtNombre = rootView.findViewById(R.id.edtNombre);

        listaRecordatorios = new ArrayList<>();
        adaptador = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaRecordatorios);
        listaDeRecordatorios.setAdapter(adaptador);

        // Cargar los recordatorios existentes al iniciar el fragmento
        cargarRecordatoriosGuardados();

        btnSeleccionarFecha.setOnClickListener(view -> {
            MaterialDatePicker.Builder<Long> calendario = MaterialDatePicker.Builder.datePicker();
            calendario.setTitleText("Seleccionar fecha");
            MaterialDatePicker<Long> datePicker = calendario.build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                String descripcion = edtNombre.getText().toString().trim();

                if (descripcion.isEmpty()) {
                    Toast.makeText(getContext(), "Escriba un recordatorio", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String fechaSeleccionada = sdf.format(new Date(selection));

                long result = managerDB.createRecordatorio(fechaSeleccionada, descripcion);
                if (result < 0) {
                    Toast.makeText(getContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Recordatorio guardado", Toast.LENGTH_SHORT).show();
                    edtNombre.setText("");
                    listaRecordatorios.add(descripcion); // 👈 solo agregamos el título
                    adaptador.notifyDataSetChanged();
                }
            });

            datePicker.show(getParentFragmentManager(), "DATE_PICKER");
        });

        return rootView;
    }

    // Método para cargar recordatorios existentes desde la base de datos
    private void cargarRecordatoriosGuardados() {
        Cursor cursor = managerDB.obtenerRecordatorios();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Ignoramos la fecha aquí
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                listaRecordatorios.add(descripcion); // 👈 solo agregamos el título
            } while (cursor.moveToNext());
            cursor.close();
        }
        adaptador.notifyDataSetChanged();
    }
}
