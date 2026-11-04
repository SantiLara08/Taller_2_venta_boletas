package com.boletas;

public class Boleta {
    private Cliente cliente;
    private Evento evento;
    private Silla silla;

    public Boleta(Cliente cliente, Evento evento, Silla silla) {
        this.cliente = cliente;
        this.evento = evento;
        this.silla = silla;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Evento getEvento() {
        return evento;
    }

    public Silla getSilla() {
        return silla;
    }
}