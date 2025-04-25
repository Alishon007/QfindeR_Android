package com.example.qfinder.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    private void openDBWr() {
        this.db = this.dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return dbHelper.getWritableDatabase();
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

    public long crearUsuario(String nombres, String apellidos, String email, String telefono, String nacimiento, String password) {
        openDBWr();
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
        openDBWr();
        ContentValues valores = new ContentValues();
        valores.put("fecha", fecha);
        long result = db.insert("recordatorio", null, valores);
        db.close();
        return result;
    }

    public boolean verificarCredenciales(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT * FROM usuario WHERE email = ? AND password = ?";
            cursor = db.rawQuery(query, new String[]{email, password});
            return cursor.moveToFirst(); // Si hay un usuario, devuelve true
        } finally {
            if (cursor != null) {
                cursor.close(); // Asegúrate de cerrar el cursor
            }
            db.close();
        }
    }

    public Cursor obtenerDatosUsuario(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombres, apellidos, email, telefono FROM usuario WHERE email = ?", new String[]{email});

        if (cursor != null && cursor.moveToFirst()) {
            return cursor; // Se encontró un usuario con ese email
        } else {
            return null; // No se encontró ningún usuario
        }
    }

    public Cursor obtenerPacientes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT id, nombres, apellidos, dependencia FROM Paciente", null);
    }

    // Método para obtener los datos de un paciente por su ID
    public Cursor obtenerPacientePorId(int pacienteId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Paciente WHERE id = ?", new String[]{String.valueOf(pacienteId)});

    }
    public long createRecordatorio(String fecha, String descripcion) {
        openDBWr();
        ContentValues valores = new ContentValues();
        valores.put("fecha", fecha);
        valores.put("descripcion", descripcion);
        long result = db.insert("recordatorio", null, valores);
        db.close();
        return result;
    }
    public Cursor obtenerRecordatorios() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT fecha, descripcion FROM recordatorio ORDER BY id DESC", null);
    }
}

