package com.boletas;

import java.time.LocalDateTime;

public abstract class Evento implements Comparable<Evento> {
    private String nombre;
    private LocalDateTime fecha;
    private Escenario escenario;
    private boolean en_preventa;
    private double descuento_preventa;

    public Evento(String nombre, LocalDateTime fecha, Escenario escenario,
                  boolean en_preventa, double descuento_preventa) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.escenario = escenario;
        this.en_preventa = en_preventa;
        this.descuento_preventa = descuento_preventa;
    }

    public abstract String tipo();

    @Override
    public int compareTo(Evento otro) {
        return this.fecha.compareTo(otro.fecha);
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public boolean estaEnPreventa() {
        return en_preventa;
    }

    public double getDescuentoPreventa() {
        return descuento_preventa;
    }

    public void setEnPreventa(boolean en_preventa) {
        this.en_preventa = en_preventa;
    }
}