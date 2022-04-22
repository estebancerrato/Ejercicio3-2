package com.example.ejercicio3_1.modelo;

public class Empleados {
    private int id;
    private String nombre;
    private String apellidos;
    private String edad;
    private String Direccion;
    private String Puesto;

    public Empleados() {
    }

    public Empleados(String nombre, String apellidos, String edad, String direccion, String puesto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        Direccion = direccion;
        Puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }
}
