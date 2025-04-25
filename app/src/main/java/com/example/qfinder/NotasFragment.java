package com.example.qfinder;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.qfinder.model.Constantes;
import com.example.qfinder.model.DbHelper;

import java.util.ArrayList;

public class NotasFragment extends Fragment {

    private ListView listaNotas;
    private ImageButton agregarNota;
    private DbHelper dbHelper;
    private ArrayList<String> notas;
    private ArrayAdapter<String> adapter;

    public NotasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaNotas = view.findViewById(R.id.listaNotasss);
        agregarNota = view.findViewById(R.id.agregarNota);
        dbHelper = new DbHelper(requireContext());
        notas = new ArrayList<>();

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, notas);
        listaNotas.setAdapter(adapter);

        cargarNotasDesdeBD();

        agregarNota.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.nav_crear_nota);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        cargarNotasDesdeBD();
        adapter.notifyDataSetChanged();
    }

    private void cargarNotasDesdeBD() {
        notas.clear();
        Cursor cursor = dbHelper.obtenerNotas();
        if (cursor.moveToFirst()) {
            do {
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow(Constantes.COLUMN_FECHA_NOTA));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(Constantes.COLUMN_DESCRIPCION_NOTA));
                notas.add(fecha + ": " + descripcion);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
