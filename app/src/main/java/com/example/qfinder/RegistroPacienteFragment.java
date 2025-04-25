package com.example.qfinder;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.qfinder.R; // Correcta
import com.example.qfinder.model.ManagerDB; // Correcta
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class RegistroPacienteFragment extends Fragment {

    private TextInputEditText etNombre, etApellido, etDependencia, etFechaNacimiento, etSexo, etEdad;
    private Button btnContinuar;
    private ManagerDB managerDB;
    private ImageView menu_icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout para este fragment
        View view = inflater.inflate(R.layout.fragment_registro_paciente, container, false);

        // Inicialización de managerDB
        managerDB = new ManagerDB(getContext());

        // Vincular componentes
        vincularComponentes(view);
        configurarEventos();

        return view;
    }

    private void vincularComponentes(View view) {
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etDependencia = view.findViewById(R.id.etDependencia);
        etFechaNacimiento = view.findViewById(R.id.etFechaNacimiento);
        etSexo = view.findViewById(R.id.etSexo);
        etEdad = view.findViewById(R.id.etEdad);

        btnContinuar = view.findViewById(R.id.btnContinuar);
        menu_icon = view.findViewById(R.id.menu_icon);
    }

    private void configurarEventos() {

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
                getContext(),
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
            String nombre = etNombre.getText().toString().trim();
            String apellido = etApellido.getText().toString().trim();
            String dependencia = etDependencia.getText().toString().trim();
            String fechaNacimiento = etFechaNacimiento.getText().toString().trim();
            String sexo = etSexo.getText().toString().trim();
            int edad = Integer.parseInt(etEdad.getText().toString().trim());

            int idGenerado = (int) (Math.random() * 100000);

            if (managerDB.insertarPaciente(idGenerado, nombre, apellido, dependencia, fechaNacimiento, sexo, edad)) {
                Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                limpiarFormulario();

                getActivity().getSharedPreferences("datos_paciente", getContext().MODE_PRIVATE)
                        .edit()
                        .putInt("paciente_id_guardado", idGenerado)
                        .apply();

                // Navegar al perfil (sin necesidad de pasar bundle ahora)
                NavController navController = NavHostFragment.findNavController(this);
                navController.navigate(R.id.action_nav_registro_paciente_to_nav_perfil_paciente);
            } else {
                Toast.makeText(getContext(), "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void limpiarFormulario() {
        etNombre.setText("");
        etApellido.setText("");
        etDependencia.setText("");
        etFechaNacimiento.setText("");
        etSexo.setText("");
        etEdad.setText("");
    }
}
