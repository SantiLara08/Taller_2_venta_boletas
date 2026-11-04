package com.boletas;

@FunctionalInterface
public interface Pago {

    boolean pagar(Cliente cliente, double pesos);
}
