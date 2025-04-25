package com.example.qfinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.qfinder.model.ManagerDB;

public class PerfilPacienteFragment extends Fragment {

    private TextView txtNombre, txtApellido, txtDependencia, txtFechaNacimiento, txtGenero, txtEdad;
    private ManagerDB managerDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_paciente, container, false);

        managerDB = new ManagerDB(getContext());

        // Vincula los TextView según tus IDs en el XML
        txtNombre = view.findViewById(R.id.txtNombre);
        txtApellido = view.findViewById(R.id.txtApellido);
        txtDependencia = view.findViewById(R.id.txtDependencia);
        txtFechaNacimiento = view.findViewById(R.id.txtFechaNacimiento);
        txtGenero = view.findViewById(R.id.txtGenero);
        txtEdad = view.findViewById(R.id.txtEdad);

        cargarDatosPaciente();

        return view;
    }

    private void cargarDatosPaciente() {
        SharedPreferences prefs = getActivity().getSharedPreferences("datos_paciente", Context.MODE_PRIVATE);
        int pacienteId = prefs.getInt("paciente_id_guardado", -1);

        if (pacienteId != -1) {
            Cursor cursor = managerDB.obtenerPacientePorId(pacienteId);
            if (cursor != null && cursor.moveToFirst()) {
                txtNombre.setText(cursor.getString(cursor.getColumnIndexOrThrow("nombres")));
                txtApellido.setText(cursor.getString(cursor.getColumnIndexOrThrow("apellidos")));
                txtDependencia.setText(cursor.getString(cursor.getColumnIndexOrThrow("dependencia")));
                txtFechaNacimiento.setText(cursor.getString(cursor.getColumnIndexOrThrow("fechaNacimiento")));
                txtGenero.setText(cursor.getString(cursor.getColumnIndexOrThrow("sexo")));
                txtEdad.setText(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("edad"))));
                cursor.close();
            } else {
                Toast.makeText(getContext(), "No se encontraron datos del paciente", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "ID de paciente no encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
