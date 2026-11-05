package com.boletas;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtilidadesTest {

    @Test
    public void ordenarDejaLosNumerosEnOrden() {
        List<Integer> numeros = new ArrayList<>(List.of(3, 1, 2));

        Utilidades.ordenar(numeros);

        assertEquals(List.of(1, 2, 3), numeros);
    }

    @Test
    public void ordenarUnaListaVaciaNoFalla() {
        List<Integer> numeros = new ArrayList<>();

        Utilidades.ordenar(numeros);

        assertTrue(numeros.isEmpty());
    }

    @Test
    public void ordenarEventosLosDejaPorFecha() {
        Partido partido1 = new Partido("Clasico", LocalDateTime.now().plusDays(5), new Escenario("A"), false, 0, "A vs B");
        Concierto concierto1 = new Concierto("Rock", LocalDateTime.now().plusDays(1), new Escenario("B"), false, 0, "Banda");
        List<Evento> eventos = new ArrayList<>(List.of(partido1, concierto1));

        Utilidades.ordenar(eventos);

        assertEquals("Rock", eventos.get(0).getNombre());
    }

    @Test
    public void contarDevuelveElTamano() {
        assertEquals(2, Utilidades.contar(List.of("a", "b")));
    }

    @Test
    public void agregarTodosMeteEnUnaListaMasGeneral() {
        List<Number> numeros = new ArrayList<>();

        Utilidades.agregarTodos(numeros, List.of(1, 2));

        assertEquals(2, numeros.size());
    }

    @Test
    public void sumarIngresosLeeCualquierParteDelEscenario() {
        Silla silla1 = new Silla(1);
        silla1.vender(80);
        Localidad localidad1 = new Localidad("VIP", 100);
        localidad1.agregar(silla1);

        assertEquals(80, Utilidades.sumarIngresos(List.of(localidad1)));
    }

    @Test
    public void recorrerLeHaceLaAccionACadaElemento() {
        List<String> copia = new ArrayList<>();

        Utilidades.recorrer(List.of("a", "b"), copia::add);

        assertEquals(List.of("a", "b"), copia);
    }
}
