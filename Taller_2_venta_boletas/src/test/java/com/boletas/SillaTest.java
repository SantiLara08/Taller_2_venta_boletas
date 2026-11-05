package com.boletas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SillaTest {

    private Silla silla;

    @BeforeEach
    public void preparar() {
        silla = new Silla(1);
    }

    @Test
    public void venderLaDejaOcupadaYConIngresos() {
        silla.vender(50000);
        assertTrue(silla.estaOcupada());
        assertEquals(50000, silla.ingresos());
    }

    @Test
    public void liberarLaDejaLibre() {
        silla.vender(50000);
        silla.liberar();
        assertFalse(silla.estaOcupada());
    }
}
