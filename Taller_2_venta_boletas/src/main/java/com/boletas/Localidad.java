package com.boletas;

public class Localidad extends Contenedor<Silla>{
    private double precio_base;

    public Localidad(String nombre,double precio_base){
        super(nombre);
        this.precio_base = precio_base;
    }

    public double getPrecioBase() {
        return precio_base;
    }
}
