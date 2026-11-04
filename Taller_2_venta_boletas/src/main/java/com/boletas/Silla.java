package com.boletas;

public class Silla implements ParteEscenario {

    private int numero;
    private boolean ocupada;
    private double precio_pagado;

    public Silla(int numero) {
        this.numero = numero;
        this.ocupada = false;
        this.precio_pagado = 0;
    }

    @Override
    public int capacidad() {
        return 1;
    }

    @Override
    public double ingresos() {
        if (ocupada) {
            return precio_pagado;
        }
        return 0;
    }

    @Override
    public int ocupadas() {
        if (ocupada) {
            return 1;
        }
        return 0;
    }

    public void vender(double precio) {
        this.ocupada = true;
        this.precio_pagado = precio;
    }

    public void liberar() {
        this.ocupada = false;
        this.precio_pagado = 0;
    }

    public int getNumero() {
        return numero;
    }

    public boolean estaOcupada() {
        return ocupada;
    }

    public double getPrecioPagado() {
        return precio_pagado;
    }
}
