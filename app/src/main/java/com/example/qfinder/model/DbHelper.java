package com.example.qfinder.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, Constantes.NAME_BD, null, Constantes.NUM_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create all tables from both branches
        db.execSQL(Constantes.SENTENCIA_TABLA); // From HEAD branch (Paciente table)
        db.execSQL(Constantes.SENTENCIA_CREAR_USUARIO); // From moriones branch (usuario table)
        db.execSQL(Constantes.SENTENCIA_CREAR_RECORDATORIO); // From moriones branch (recordatorio table)
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Typically you would handle database migrations here
        // For now leaving it empty as in both branches
    }
}