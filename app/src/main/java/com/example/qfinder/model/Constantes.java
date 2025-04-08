package com.example.qfinder.model;

public class Constantes {
    public static String NAME_BD ="qfinderZulema2";
    public static  int NUM_VERSION = 1;
    public static String SENTENCIA_CREAR_USUARIO = "create table usuario(" +
                "nombres VARCHAR(100) NOT NULL," +
                "apellidos VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) NOT NULL," +
                "telefono VARCHAR(10)," +
                "nacimiento DATE," +
                "password VARCHAR(20) NOT NULL" +
            ");";
    public static String SENTENCIA_CREAR_RECORDATORIO = "CREATE TABLE recordatorio(fecha DATE)";
}
