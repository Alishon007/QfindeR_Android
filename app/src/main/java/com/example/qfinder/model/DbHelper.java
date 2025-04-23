package com.example.qfinder.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    // Datos de la base de datos
    private static final String DATABASE_NAME = "QfinderDB.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla de notas
    public static final String TABLE_NOTAS = "notas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_DESCRIPCION = "descripcion";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tablas definidas en la clase Constantes (como paciente, usuario, recordatorio, etc.)
        db.execSQL(Constantes.SENTENCIA_TABLA); // Tabla Paciente
        db.execSQL(Constantes.SENTENCIA_CREAR_USUARIO); // Tabla Usuario
        db.execSQL(Constantes.SENTENCIA_CREAR_RECORDATORIO); // Tabla Recordatorio

        // Crear tabla de notas
        String createNotasTable = "CREATE TABLE " + TABLE_NOTAS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FECHA + " TEXT, " +
                COLUMN_DESCRIPCION + " TEXT)";
        db.execSQL(createNotasTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar y recrear tablas si se actualiza la versión
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTAS);
        db.execSQL("DROP TABLE IF EXISTS paciente"); // Asegúrate de que coincida con el nombre real
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS recordatorio");
        onCreate(db);
    }

    // --- Funciones para la tabla de notas ---

    public boolean insertarNota(String fecha, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FECHA, fecha);
        values.put(COLUMN_DESCRIPCION, descripcion);

        long result = db.insert(TABLE_NOTAS, null, values);
        return result != -1;
    }

    public Cursor obtenerNotas() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NOTAS, null);
    }
}
