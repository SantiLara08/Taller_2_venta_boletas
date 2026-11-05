package com.boletas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteServicioTest {
    private ReporteServicio reporte1;
    private Concierto concierto1;
    private Partido partido1;
    private ObraTeatro obra1;

    private Escenario crearEscenario(int sillasVendidas, int sillasLibres, double precio) {
        Localidad localidad1 = new Localidad("General", precio);
        for (int i = 1; i <= sillasVendidas; i++) {
            Silla silla1 = new Silla(i);
            silla1.vender(precio);
            localidad1.agregar(silla1);
        }
        for (int i = 1; i <= sillasLibres; i++) {
            localidad1.agregar(new Silla(sillasVendidas + i));
        }
        Zona zona1 = new Zona("Oriental");
        zona1.agregar(localidad1);
        Escenario escenario1 = new Escenario("Estadio");
        escenario1.agregar(zona1);
        return escenario1;
    }

    @BeforeEach
    public void preparar() {
        concierto1 = new Concierto("Rock", LocalDateTime.now().plusDays(10), crearEscenario(3, 5, 100), false, 0, "Banda");
        partido1 = new Partido("Clasico", LocalDateTime.now().plusDays(3), crearEscenario(1, 3, 100), false, 0, "A vs B");
        obra1 = new ObraTeatro("Hamlet", LocalDateTime.now().minusDays(1), new Escenario("Vacio"), false, 0, "Juan");

        Registro<Evento> eventos = new RegistroLista<>();
        eventos.agregar(concierto1);
        eventos.agregar(partido1);
        eventos.agregar(obra1);
        reporte1 = new ReporteServicio(eventos);
    }

    @Test
    public void ocupacionDaElPorcentaje() {
        assertEquals(37.5, reporte1.ocupacion(concierto1));
        assertEquals(25, reporte1.ocupacion(partido1));
    }

    @Test
    public void ocupacionSinSillasDaCero() {
        assertEquals(0, reporte1.ocupacion(obra1));
    }

    @Test
    public void ocupacionPromedioPromediaLosEventos() {
        assertEquals(62.5 / 3, reporte1.ocupacionPromedio(), 0.001);
    }

    @Test
    public void recaudacionPorTipoSeparaLosTipos() {
        Map<String, Double> recaudacion = reporte1.recaudacionPorTipo();

        assertEquals(300, recaudacion.get("Concierto"));
        assertEquals(100, recaudacion.get("Partido"));
        assertEquals(3, recaudacion.size());
    }

    @Test
    public void recaudacionTotalSumaTodo() {
        assertEquals(400, reporte1.recaudacionTotal());
    }

    @Test
    public void calendarioOrdenaYQuitaLosPasados() {
        List<Evento> calendario = reporte1.calendario();

        assertEquals(2, calendario.size());
        assertEquals("Clasico", calendario.get(0).getNombre());
    }

    @Test
    public void buscarEventoEncuentraPorNombre() {
        assertTrue(reporte1.buscarEvento("Rock").isPresent());
        assertFalse(reporte1.buscarEvento("Festival").isPresent());
    }

    @Test
    public void eventosPorTipoYNombreUsaElComparadorCompuesto() {
        List<Evento> eventos = reporte1.eventosPorTipoYNombre();

        assertEquals("Clasico", eventos.get(0).getNombre());
        assertEquals("Rock", eventos.get(2).getNombre());
    }
}
