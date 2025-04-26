package com.example.qfinder.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qfinder.MenuQfinder;
import com.example.qfinder.databinding.FragmentLoginBinding;
import com.example.qfinder.model.ManagerDB;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private ManagerDB managerDB;

    public LoginFragment() {
        // Constructor público vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        managerDB = new ManagerDB(requireContext());

        binding.txtRegistrarme.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RegistroUsuario.class);
            startActivity(intent);
        });

        binding.btnIniciarSesion.setOnClickListener(v -> {
            String email = binding.edtEmail.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean credencialesValidas = managerDB.verificarCredenciales(email, password);
            if (credencialesValidas) {
                Cursor cursor = managerDB.obtenerDatosUsuario(email);
                if (cursor.moveToFirst()) {
                    String nombres = cursor.getString(0);
                    String apellidos = cursor.getString(1);
                    String telefono = cursor.getString(3);

                    Intent intent = new Intent(getActivity(), MenuQfinder.class);
                    intent.putExtra("nombre", nombres + " " + apellidos);
                    intent.putExtra("usuario", nombres);
                    intent.putExtra("contacto", telefono);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    requireActivity().finish();
                } else {
                    Toast.makeText(getContext(), "Error al obtener datos del usuario", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
