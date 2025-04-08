package com.example.qfinder.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NotasDB.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "notas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_DESCRIPCION = "descripcion";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FECHA + " TEXT," +
                COLUMN_DESCRIPCION + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertarNota(String fecha, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FECHA, fecha);
        values.put(COLUMN_DESCRIPCION, descripcion);

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public Cursor obtenerNotas() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
