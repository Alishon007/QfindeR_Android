package com.example.qfinder.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ManagerDB {
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public ManagerDB(Context context) {
        this.context = context;
        this.dbHelper = new DbHelper(context);
    }

    // Método público para acceso a la base de datos
    public SQLiteDatabase getWritableDatabase() {
        return dbHelper.getWritableDatabase();
    }

    private void openDBWr() {
        this.db = this.dbHelper.getWritableDatabase();
    }

    public boolean insertarPaciente(int id, String nombres, String apellidos,
                                    String dependencia, String fechaNacimiento,
                                    String sexo, int edad) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
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
    public long crearUsuario(String nombres, String apellidos, String email, String telefono, String nacimiento, String password){
        //1.Abrir la BD en modo escritura
        openDBWr();
        //2.Crear un contenedor de valores para almacenar columbas y datos a insertar
        ContentValues valores = new ContentValues();
        valores.put("nombres", nombres);
        valores.put("apellidos", apellidos);
        valores.put("email", email);
        valores.put("telefono", telefono);
        valores.put("nacimiento", nacimiento);
        valores.put("password", password);
        long result = db.insert("usuario", null, valores);
        return result;
    }
    public long createRecordatorio(String fecha) {
        System.out.println("Fecha registro: " + fecha);
        openDBWr();
        ContentValues valores = new ContentValues();
        valores.put("fecha", fecha);
        long result = db.insert("recordatorio", null, valores);
        db.close();
        return result;
    }
}