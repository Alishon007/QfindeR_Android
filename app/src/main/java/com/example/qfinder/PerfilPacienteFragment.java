package com.example.qfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qfinder.model.ManagerDB;

public class PerfilPacienteFragment extends Fragment {

    private TextView txtNombre, txtApellido, txtDependencia, txtFechaNacimiento, txtGenero, txtEdad;
    private ManagerDB managerDB;
    private LinearLayout btnEditar;

    private static final int EDITAR_PERFIL_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_paciente, container, false);

        managerDB = new ManagerDB(getContext());

        txtNombre = view.findViewById(R.id.txtNombre);
        txtApellido = view.findViewById(R.id.txtApellido);
        txtDependencia = view.findViewById(R.id.txtDependencia);
        txtFechaNacimiento = view.findViewById(R.id.txtFechaNacimiento);
        txtGenero = view.findViewById(R.id.txtGenero);
        txtEdad = view.findViewById(R.id.txtEdad);
        btnEditar = view.findViewById(R.id.btnEditar);

        cargarDatosPaciente();

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditarPerfilPacienteActivity.class);
            // Enviamos los datos actuales al intent
            intent.putExtra("nombre", txtNombre.getText().toString());
            intent.putExtra("apellido", txtApellido.getText().toString());
            intent.putExtra("dependencia", txtDependencia.getText().toString());
            intent.putExtra("fechaNacimiento", txtFechaNacimiento.getText().toString());
            intent.putExtra("genero", txtGenero.getText().toString());
            intent.putExtra("edad", txtEdad.getText().toString());

            startActivityForResult(intent, EDITAR_PERFIL_REQUEST_CODE);
        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDITAR_PERFIL_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            // Actualiza los campos con los datos editados
            txtNombre.setText(data.getStringExtra("nombre"));
            txtApellido.setText(data.getStringExtra("apellido"));
            txtDependencia.setText(data.getStringExtra("dependencia"));
            txtFechaNacimiento.setText(data.getStringExtra("fechaNacimiento"));
            txtGenero.setText(data.getStringExtra("genero"));
            txtEdad.setText(data.getStringExtra("edad"));
        }
    }
}
