package com.boletas;

import java.time.LocalDateTime;

public class ObraTeatro extends Evento {
    private String director;

    public ObraTeatro(String nombre, LocalDateTime fecha, Escenario escenario,
                      boolean en_preventa, double descuento_preventa, String director) {
        super(nombre, fecha, escenario, en_preventa, descuento_preventa);
        this.director = director;
    }

    @Override
    public String tipo() {
        return "Obra de teatro";
    }

    public String getDirector() {
        return director;
    }
}