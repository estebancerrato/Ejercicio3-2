package com.example.ejercicio3_1.modelo;

public class Transacciones {
    //Nombre de la base de datos
    public static final String NameDatabase = "PM01DB";

    //Creacion de las tablas de la base de datos
    public static final String tablapersonas = "personas";
       //Creacion de los atributos
        public static final String id = "id";
        public static final String nombres = "nombres";
        public static final String apellidos = "apellidos";
        public static final String edad = "edad";
        public static final String direccion = "direccion";
        public static final String puesto = "puesto";



        //Transacciones DDL
        public static final String createTablePersonas = "CREATE TABLE " + tablapersonas +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres TEXT, apellidos TEXT, edad INTEGER, direccion TEXT, puesto TEXT)";

        public static final String dropTablePersonas = "DROP TABLE IF EXIST" + tablapersonas;
}
