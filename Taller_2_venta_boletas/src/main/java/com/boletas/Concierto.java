package com.boletas;

import java.time.LocalDateTime;

public class Concierto extends Evento {
    private String artista;

    public Concierto(String nombre, LocalDateTime fecha, Escenario escenario,
                     boolean en_preventa, double descuento_preventa, String artista) {
        super(nombre, fecha, escenario, en_preventa, descuento_preventa);
        this.artista = artista;
    }

    @Override
    public String tipo() {
        return "Concierto";
    }

    public String getArtista() {
        return artista;
    }
}