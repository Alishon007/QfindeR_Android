package com.example.qfinder.model;

public class Constantes {
    public static final String NAME_BD = "qfinderZulema2";
    public static final int NUM_VERSION = 1;

    // From HEAD branch (original)
    public static final String SENTENCIA_TABLA = "create table Paciente(" +
            "id integer, " +
            "nombres text, " +
            "apellidos text, " +
            "dependencia text , " +
            "fechaNacimiento text, " +
            "sexo text, " +
            "edad integer)";

    // From moriones branch
    public static final String SENTENCIA_CREAR_USUARIO = "create table usuario(" +
            "nombres VARCHAR(100) NOT NULL," +
            "apellidos VARCHAR(100) NOT NULL," +
            "email VARCHAR(100) NOT NULL," +
            "telefono VARCHAR(10)," +
            "nacimiento DATE," +
            "password VARCHAR(20) NOT NULL" +
            ");";

    public static final String SENTENCIA_CREAR_RECORDATORIO = "CREATE TABLE recordatorio(fecha DATE)";
}