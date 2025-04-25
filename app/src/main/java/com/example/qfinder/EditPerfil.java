package com.example.qfinder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.navigation.Navigation;

import java.io.IOException;

public class EditPerfil extends Fragment {

    private EditText etNombre, etUsuario, etContacto, etEmail;
    private Button btnGuardar;
    private ImageView imgPerfilEditar;
    private Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;

    // Declaramos el ActivityResultLauncher
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    public EditPerfil() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View rootView = inflater.inflate(R.layout.fragment_edit_perfil, container, false);

        // Referencias a las vistas
        etNombre = rootView.findViewById(R.id.etNombre);
        etUsuario = rootView.findViewById(R.id.etUsuario);
        etContacto = rootView.findViewById(R.id.etContacto);
        etEmail = rootView.findViewById(R.id.etEmail);
        btnGuardar = rootView.findViewById(R.id.btnGuardar);
        imgPerfilEditar = rootView.findViewById(R.id.imgPerfilEditar);

        // Inicializamos el lanzador de la actividad para seleccionar imágenes
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                Uri selectedImageUri = result.getData().getData();
                if (selectedImageUri != null) {
                    imageUri = selectedImageUri;
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                        imgPerfilEditar.setImageBitmap(bitmap); // Mostrar la nueva imagen seleccionada
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Obtener datos del Bundle (si existen)
        Bundle bundle = getArguments();
        if (bundle != null) {
            etNombre.setText(bundle.getString("nombre"));
            etUsuario.setText(bundle.getString("usuario"));
            etContacto.setText(bundle.getString("contacto"));
            etEmail.setText(bundle.getString("email"));
            // Si ya hay una imagen, se puede cargar también (si se pasa como URI o Bitmap)
            String imageUriString = bundle.getString("imagenUri");
            if (imageUriString != null) {
                imageUri = Uri.parse(imageUriString);
                imgPerfilEditar.setImageURI(imageUri);
            }
        }

        // Acción de la imagen de perfil (para cambiar la imagen)
        imgPerfilEditar.setOnClickListener(view -> openImagePicker());

        // Acción del botón Guardar
        btnGuardar.setOnClickListener(view -> {
            // Guardar los datos de texto y la imagen (por ejemplo, como URI o Bitmap)
            Bundle result = new Bundle();
            result.putString("nombre", etNombre.getText().toString());
            result.putString("usuario", etUsuario.getText().toString());
            result.putString("contacto", etContacto.getText().toString());
            result.putString("email", etEmail.getText().toString());

            // Guardar la URI de la imagen seleccionada
            if (imageUri != null) {
                result.putString("imagenUri", imageUri.toString());
            }

            // Navegar de regreso al perfil con los nuevos datos
            Navigation.findNavController(view).navigate(R.id.action_nav_editar_perfil_to_nav_perfil, result);
        });

        return rootView;
    }

    // Método para abrir la galería y seleccionar una imagen
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);  // Usar el lanzador para abrir el selector
    }
}
