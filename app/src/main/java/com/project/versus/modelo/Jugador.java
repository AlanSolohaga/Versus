package com.project.versus.modelo;

public class Jugador {
    String nombre;
    EnumCalidad calidad;

    public Jugador(String nombre, EnumCalidad calidad) {
        this.nombre = nombre;
        this.calidad = calidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EnumCalidad getCalidad() {
        return calidad;
    }

    public void setCalidad(EnumCalidad calidad) {
        this.calidad = calidad;
    }
}
