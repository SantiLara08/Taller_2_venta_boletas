package com.boletas;

import java.time.LocalDateTime;

public class Partido extends Evento {
    private String equipos;

    public Partido(String nombre, LocalDateTime fecha, Escenario escenario,
                   boolean en_preventa, double descuento_preventa, String equipos) {
        super(nombre, fecha, escenario, en_preventa, descuento_preventa);
        this.equipos = equipos;
    }

    @Override
    public String tipo() {
        return "Partido";
    }

    public String getEquipos() {
        return equipos;
    }
}