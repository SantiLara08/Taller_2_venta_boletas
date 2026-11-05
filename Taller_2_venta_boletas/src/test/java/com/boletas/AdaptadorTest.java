package com.boletas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdaptadorTest {

    private Cliente cliente;
    private AdaptadorA adaptadorA;
    private AdaptadorB adaptadorB;

    @BeforeEach
    public void preparar() {
        cliente = new Cliente("Cliente 1", "111");
        adaptadorA = new AdaptadorA(new ProveedorA());
        adaptadorB = new AdaptadorB(new ProveedorB());
    }

    @Test
    public void adaptadorAPagaCorrectamente() {
        assertTrue(adaptadorA.pagar(cliente, 50000.0));
    }

    @Test
    public void adaptadorBPagaCorrectamente() {
        assertTrue(adaptadorB.pagar(cliente, 50000.0));
    }

    @Test
    public void pagoSePuedeUsarComoLambda() {
        Pago pagoSimulado = (c, pesos) -> pesos > 0;
        assertTrue(pagoSimulado.pagar(cliente, 100.0));
        assertFalse(pagoSimulado.pagar(cliente, -5.0));
    }
}
