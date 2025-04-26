package com.example.qfinder.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, Constantes.NAME_BD, null, Constantes.NUM_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constantes.SENTENCIA_TABLA_PACIENTE);
        db.execSQL(Constantes.SENTENCIA_CREAR_USUARIO);
        db.execSQL(Constantes.SENTENCIA_CREAR_RECORDATORIO);
        db.execSQL(Constantes.SENTENCIA_CREAR_NOTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Paciente");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS recordatorio");
        db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLE_NOTAS);
        onCreate(db);
    }

    // Función para insertar notas
    public boolean insertarNota(String fecha, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constantes.COLUMN_FECHA_NOTA, fecha);
        values.put(Constantes.COLUMN_DESCRIPCION_NOTA, descripcion);
        long result = db.insert(Constantes.TABLE_NOTAS, null, values);
        return result != -1;
    }

    // Obtener todas las notas
    public Cursor obtenerNotas() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + Constantes.TABLE_NOTAS, null);
    }

    // Obtener notas filtradas por fecha
    public Cursor obtenerNotasPorFecha(String fechaBusqueda) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Usamos LIKE para permitir coincidencias parciales con la fecha
        String query = "SELECT * FROM " + Constantes.TABLE_NOTAS +
                " WHERE " + Constantes.COLUMN_FECHA_NOTA + " LIKE ?";
        return db.rawQuery(query, new String[]{"%" + fechaBusqueda + "%"});
    }
}
