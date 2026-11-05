package com.boletas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VentaServicioTest {
    private VentaServicio venta1;
    private Cliente cliente1;
    private Localidad localidad1;
    private Concierto concierto1;

    private Concierto crearConcierto(boolean en_preventa, int horas) {
        Zona zona1 = new Zona("Zona 1");
        zona1.agregar(localidad1);
        Escenario escenario1 = new Escenario("Estadio");
        escenario1.agregar(zona1);
        return new Concierto("Concierto de Rock", LocalDateTime.now().plusHours(horas), escenario1, en_preventa, 0.2, "Banda");
    }

    private Silla silla(int posicion) {
        return localidad1.getPartes().get(posicion);
    }

    @BeforeEach
    public void preparar() {
        venta1 = new VentaServicio(new AdaptadorA(new ProveedorA()));
        cliente1 = new Cliente("Cliente 1", "111");
        localidad1 = new Localidad("VIP", 100);
        for (int i = 1; i <= 6; i++) {
            localidad1.agregar(new Silla(i));
        }
        concierto1 = crearConcierto(false, 240);
    }

    @Test
    public void venderOcupaLaSillaYGuardaLaBoleta() throws BoletasException {
        Boleta boleta1 = venta1.vender(cliente1, concierto1, localidad1, silla(0));

        assertTrue(silla(0).estaOcupada());
        assertEquals(100, boleta1.getSilla().getPrecioPagado());
        assertEquals(1, venta1.getBoletas().size());
    }

    @Test
    public void enPreventaSeCobraConDescuento() throws BoletasException {
        Concierto concierto2 = crearConcierto(true, 240);

        venta1.vender(cliente1, concierto2, localidad1, silla(0));

        assertEquals(80, silla(0).getPrecioPagado());
    }

    @Test
    public void noSePuedeVenderUnaSillaOcupada() throws BoletasException {
        venta1.vender(cliente1, concierto1, localidad1, silla(0));
        Cliente cliente2 = new Cliente("Cliente 2", "222");

        assertThrows(SillaOcupadaException.class, () -> venta1.vender(cliente2, concierto1, localidad1, silla(0)));
        assertEquals(1, venta1.getBoletas().size());
    }

    @Test
    public void laQuintaBoletaPasaElLimite() throws BoletasException {
        for (int i = 0; i < 4; i++) {
            venta1.vender(cliente1, concierto1, localidad1, silla(i));
        }

        assertThrows(LimiteBoletasException.class, () -> venta1.vender(cliente1, concierto1, localidad1, silla(4)));
        assertFalse(silla(4).estaOcupada());
    }

    @Test
    public void elLimiteCuentaPorDocumento() throws BoletasException {
        for (int i = 0; i < 4; i++) {
            venta1.vender(cliente1, concierto1, localidad1, silla(i));
        }
        Cliente mismoDocumento = new Cliente("Cliente 3", "111");

        assertThrows(LimiteBoletasException.class, () -> venta1.vender(mismoDocumento, concierto1, localidad1, silla(4)));
    }

    @Test
    public void siElCobroFallaLaSillaQuedaLibre() {
        VentaServicio venta2 = new VentaServicio((cliente, pesos) -> false);

        assertThrows(BoletasException.class, () -> venta2.vender(cliente1, concierto1, localidad1, silla(0)));
        assertFalse(silla(0).estaOcupada());
    }

    @Test
    public void cancelarConTiempoLiberaLaSilla() throws BoletasException {
        Boleta boleta1 = venta1.vender(cliente1, concierto1, localidad1, silla(0));

        venta1.cancelar(boleta1);

        assertFalse(silla(0).estaOcupada());
        assertEquals(0, venta1.getBoletas().size());
    }

    @Test
    public void noSePuedeCancelarAMenosDe24Horas() throws BoletasException {
        Concierto concierto2 = crearConcierto(false, 12);
        Boleta boleta1 = venta1.vender(cliente1, concierto2, localidad1, silla(0));

        assertThrows(CancelacionFueraDeTiempoException.class, () -> venta1.cancelar(boleta1));
        assertTrue(silla(0).estaOcupada());
    }
}
