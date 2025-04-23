package com.example.qfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.qfinder.controller.ListaNotas;
import com.example.qfinder.controller.Login;
import com.example.qfinder.controller.MainPerfil;
import com.example.qfinder.controller.PerfilPaciente;
import com.example.qfinder.controller.RegistroPaciente;
import com.example.qfinder.model.ManagerDB;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Recordatorio extends AppCompatActivity {
    ManagerDB managerDB;
    Button btnSeleccionarFecha;
    ListView listaDeRecordatorios;

    DrawerLayout drawerLayout;
    ImageView menu_icon;

    Button btnReconrdatorio;
    Button btRegistroPaciente;
    Button btnNotas;
    Button btnPerfilPaciente;
    Button btnPerfil, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorio);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu_icon = findViewById(R.id.menu_icon);

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        btnReconrdatorio = findViewById(R.id.btnRecordatorio);
        btRegistroPaciente = findViewById(R.id.btnRegitroPaciente);
        btnNotas = findViewById(R.id.btnNotas);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnPerfilPaciente = findViewById(R.id.btnPerfilPaciente);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(Recordatorio.this, Login.class);
            startActivity(intent);
        });


        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(Recordatorio.this, MainPerfil.class);
            startActivity(intent);
        });
        btnReconrdatorio.setOnClickListener(v -> {
            Intent intent = new Intent(Recordatorio.this, Recordatorio.class);
            startActivity(intent);
        });
        btnNotas.setOnClickListener(v -> {
            Intent intent = new Intent(Recordatorio.this, ListaNotas.class);
            startActivity(intent);
        });
        btRegistroPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(Recordatorio.this, RegistroPaciente.class);
            startActivity(intent);
        });
        btnPerfilPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(Recordatorio.this, PerfilPaciente.class);
            startActivity(intent);
        });

        managerDB = new ManagerDB(Recordatorio.this);

        List<String> listaFechas = new ArrayList<>();
        listaFechas.add("01/01/2024");
        listaFechas.add("15/02/2024");

        btnSeleccionarFecha = findViewById(R.id.btnSeleccionarFecha);
        listaDeRecordatorios = findViewById(R.id.listaDeRecordatorios);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaFechas);
        listaDeRecordatorios.setAdapter(adaptador);

        btnSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos una instancia de MaterialDatePicker para construir nuestro calendario
                MaterialDatePicker.Builder<Long> calendario = MaterialDatePicker.Builder.datePicker();
                //Le agregamos un titulo  al campo de la fecha
                calendario.setTitleText("Seleccionar fecha");
                //asignamos a una variable tipo MaterialDatePicker el titulo con el metodo build de la instancia calendario
                MaterialDatePicker<Long> datePicker = calendario.build();

                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String fechaSeleccionada = sdf.format(new Date(selection));
                        Toast.makeText(Recordatorio.this, "Fecha seleccionada: " + fechaSeleccionada, Toast.LENGTH_SHORT).show();
                        listaFechas.add(fechaSeleccionada);

                        listaDeRecordatorios.setAdapter(adaptador);

                        long result = managerDB.createRecordatorio(fechaSeleccionada);

                        if(result < 0){
                            Toast.makeText(Recordatorio.this, "Hubieron fallos", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Recordatorio.this, "Se registro la fecha", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });
    }
}