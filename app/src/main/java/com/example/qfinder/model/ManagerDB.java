package com.example.qfinder.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ManagerDB  {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public ManagerDB(Context context) {
        dbHelper = new DbHelper(context);
    }


    //metodo para abrir la base de datos en modo escritura
    public void openDBWr(){
        db = dbHelper.getWritableDatabase();
    }

    //metodo para abrir la base de datos en modo lectura
    public void openDbRd(){
        db = dbHelper.getReadableDatabase();
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
}
