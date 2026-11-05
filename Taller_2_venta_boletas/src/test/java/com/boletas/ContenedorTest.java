package com.boletas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContenedorTest {
    private Silla silla1;
    private Silla silla2;
    private Silla silla3;
    private Localidad localidad1;
    private Localidad localidad2;
    private Zona zona1;
    private Escenario escenario1;

    @BeforeEach
    public void preparar() {
        silla1 = new Silla(1);
        silla2 = new Silla(2);
        silla3 = new Silla(3);

        localidad1 = new Localidad("VIP", 100);
        localidad1.agregar(silla1);
        localidad1.agregar(silla2);

        localidad2 = new Localidad("General", 50);
        localidad2.agregar(silla3);

        zona1 = new Zona("Zona 1");
        zona1.agregar(localidad1);
        zona1.agregar(localidad2);

        escenario1 = new Escenario("Estadio");
        escenario1.agregar(zona1);
    }

    @Test
    public void capacidadSumaEnCadaNivel() {
        assertEquals(1, silla1.capacidad());
        assertEquals(2, localidad1.capacidad());
        assertEquals(3, zona1.capacidad());
        assertEquals(3, escenario1.capacidad());
    }

    @Test
    public void ingresosSumanLoVendidoEnCadaNivel() {
        silla1.vender(80);
        silla3.vender(50);

        assertEquals(80, localidad1.ingresos());
        assertEquals(130, zona1.ingresos());
        assertEquals(130, escenario1.ingresos());
    }

    @Test
    public void ocupadasCuentaSoloLasVendidas() {
        silla1.vender(80);

        assertEquals(1, localidad1.ocupadas());
        assertEquals(0, localidad2.ocupadas());
        assertEquals(1, escenario1.ocupadas());
    }

    @Test
    public void liberarUnaSillaBajaLosIngresos() {
        silla1.vender(80);
        silla1.liberar();

        assertEquals(0, escenario1.ingresos());
        assertEquals(0, escenario1.ocupadas());
    }

    @Test
    public void contenedorVacioRespondeCero() {
        Zona zona2 = new Zona("Zona 2");

        assertEquals(0, zona2.capacidad());
        assertEquals(0, zona2.ingresos());
    }
}
