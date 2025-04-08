package com.example.qfinder.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ManagerDB {

    public DbHelper dbHelper;
    private Context context;

    public ManagerDB(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    public boolean insertarPaciente(int id, String nombres, String apellidos, String dependencia, String fechaNacimiento, String sexo, int edad) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", id);
        values.put("nombres", nombres);
        values.put("apellidos", apellidos);
        values.put("dependencia", dependencia);
        values.put("fechaNacimiento", fechaNacimiento);
        values.put("sexo", sexo);
        values.put("edad", edad);

        long resultado = db.insert("Paciente", null, values);
        db.close();

        if (resultado == -1) {
            Toast.makeText(context, "Error al registrar paciente", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Paciente registrado correctamente", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}