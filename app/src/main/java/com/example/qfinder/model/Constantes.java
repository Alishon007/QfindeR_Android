package com.example.qfinder.model;

public class Constantes {
    public static final String NAME_BD = "QfinderDB.db";
    public static final int NUM_VERSION = 3;

    // Tabla Paciente
    public static final String SENTENCIA_TABLA_PACIENTE = "CREATE TABLE Paciente (" +
            "id INTEGER, " +
            "nombres TEXT, " +
            "apellidos TEXT, " +
            "dependencia TEXT, " +
            "fechaNacimiento TEXT, " +
            "sexo TEXT, " +
            "edad INTEGER" +
            ");";

    // Tabla Usuario
    public static final String SENTENCIA_CREAR_USUARIO = "CREATE TABLE usuario (" +
            "nombres TEXT NOT NULL, " +
            "apellidos TEXT NOT NULL, " +
            "email TEXT NOT NULL, " +
            "telefono TEXT, " +
            "nacimiento TEXT, " +
            "password TEXT NOT NULL" +
            ");";

    // Tabla Recordatorio
    public static final String SENTENCIA_CREAR_RECORDATORIO = "CREATE TABLE recordatorio (" +
            "id INTEGER, " +
            "fecha TEXT, " +
            "descripcion TEXT" +
            ");";

    // Tabla Notas
    public static final String TABLE_NOTAS = "notas";
    public static final String SENTENCIA_CREAR_NOTAS = "CREATE TABLE " + TABLE_NOTAS + " (" +
            "id INTEGER, " +
            "fecha TEXT, " +
            "descripcion TEXT" +
            ");";

    // Constantes columnas tabla Notas
    public static final String COLUMN_FECHA_NOTA = "fecha";
    public static final String COLUMN_DESCRIPCION_NOTA = "descripcion";

}
