package com.example.qfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorio);

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