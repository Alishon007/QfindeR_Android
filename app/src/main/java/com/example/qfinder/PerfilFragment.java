package com.example.qfinder.ui.perfil;

import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.navigation.Navigation;

import com.example.qfinder.R;

public class PerfilFragment extends Fragment {

    private TextView txtNombre, txtUsuario, txtContacto, txtEmail;
    private ImageView imgPerfil;
    private Button btnEditar;

    private String imagenUri;  // Variable para almacenar la URI de la imagen

    public PerfilFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Vincular vistas
        txtNombre = view.findViewById(R.id.txtNombre);
        txtUsuario = view.findViewById(R.id.txtUsuario);
        txtContacto = view.findViewById(R.id.txtContacto);
        txtEmail = view.findViewById(R.id.txtEmail);
        imgPerfil = view.findViewById(R.id.imgPerfil);
        btnEditar = view.findViewById(R.id.btnEditar);

        // Obtener los datos del Bundle pasado desde MenuQfinder
        Bundle datosUsuario = getArguments();
        if (datosUsuario != null) {
            String nombreCompleto = datosUsuario.getString("nombre", "Nombre Apellido");
            String usuario = datosUsuario.getString("usuario", "Usuario");
            String contacto = datosUsuario.getString("contacto", "0000000000");
            String email = datosUsuario.getString("email", "correo@ejemplo.com");
            imagenUri = datosUsuario.getString("imagenUri", null);

            // Asignar los datos a los TextView
            txtNombre.setText(nombreCompleto);
            txtUsuario.setText(usuario);
            txtContacto.setText(contacto);
            txtEmail.setText(email);

            // Mostrar la imagen si existe
            if (imagenUri != null) {
                imgPerfil.setImageURI(Uri.parse(imagenUri));
            }
        }

        // Botón Editar
        btnEditar.setOnClickListener(v -> {
            Bundle bundleEdicion = new Bundle();
            bundleEdicion.putString("nombre", txtNombre.getText().toString());
            bundleEdicion.putString("usuario", txtUsuario.getText().toString());
            bundleEdicion.putString("contacto", txtContacto.getText().toString());
            bundleEdicion.putString("email", txtEmail.getText().toString());
            bundleEdicion.putString("imagenUri", imagenUri);

            Navigation.findNavController(v).navigate(R.id.action_nav_perfil_to_editPerfil, bundleEdicion);
        });

        return view;
    }

    // Método para actualizar los datos cuando regreses del fragmento de edición
    public void actualizarPerfil(Bundle result) {
        if (result != null) {
            txtNombre.setText(result.getString("nombre"));
            txtUsuario.setText(result.getString("usuario"));
            txtContacto.setText(result.getString("contacto"));
            txtEmail.setText(result.getString("email"));

            String nuevaImagenUri = result.getString("imagenUri");
            if (nuevaImagenUri != null) {
                imagenUri = nuevaImagenUri;
                imgPerfil.setImageURI(Uri.parse(imagenUri));
            }
        }
    }
}
