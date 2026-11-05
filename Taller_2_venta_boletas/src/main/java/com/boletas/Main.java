package com.boletas;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        Scanner teclado = new Scanner(System.in);

        //Creacion del escenario del concierto
        Silla silla1 = new Silla(1);
        Silla silla2 = new Silla(2);
        Silla silla3 = new Silla(3);
        Silla silla4 = new Silla(4);
        Silla silla5 = new Silla(5);
        Silla silla6 = new Silla(6);
        Silla silla7 = new Silla(7);
        Silla silla8 = new Silla(8);

        Localidad localidad1 = new Localidad("VIP", 200000);
        localidad1.agregar(silla1);
        localidad1.agregar(silla2);

        Localidad localidad2 = new Localidad("General", 100000);
        localidad2.agregar(silla3);
        localidad2.agregar(silla4);
        localidad2.agregar(silla5);
        localidad2.agregar(silla6);
        localidad2.agregar(silla7);

        Localidad localidad3 = new Localidad("Platea", 150000);
        localidad3.agregar(silla8);

        Zona zona1 = new Zona("Zona 1");
        zona1.agregar(localidad1);
        zona1.agregar(localidad2);
        zona1.agregar(localidad3);

        Escenario escenario1 = new Escenario("Parque");
        escenario1.agregar(zona1);



        //Creacion del escenario del partido
        Silla silla9 = new Silla(1);
        Silla silla10 = new Silla(2);

        Localidad localidad4 = new Localidad("General", 80000);
        localidad4.agregar(silla9);
        localidad4.agregar(silla10);

        Zona zona2 = new Zona("Zona 2");
        zona2.agregar(localidad4);

        Escenario escenario2 = new Escenario("Estadio");
        escenario2.agregar(zona2);



        //Creacion del escenario de la obra
        Silla silla11 = new Silla(1);

        Localidad localidad5 = new Localidad("Platea", 120000);
        localidad5.agregar(silla11);

        Zona zona3 = new Zona("Zona 3");
        zona3.agregar(localidad5);

        Escenario escenario3 = new Escenario("Teatro");
        escenario3.agregar(zona3);



        //Creacion de los eventos
        Concierto concierto1 = new Concierto("Concierto de Rock", LocalDateTime.now().plusDays(10), escenario1, true, 0.2, "Banda");
        Partido partido1 = new Partido("Partido de Futbol", LocalDateTime.now().plusDays(3), escenario2, false, 0, "Equipo A vs Equipo B");
        ObraTeatro obra1 = new ObraTeatro("Obra de Teatro", LocalDateTime.now().plusHours(12), escenario3, false, 0, "Director");

        List<Evento> todos = new ArrayList<>();
        Utilidades.agregarTodos(todos, List.of(concierto1));
        Utilidades.agregarTodos(todos, List.of(partido1));
        Utilidades.agregarTodos(todos, List.of(obra1));

        Registro<Evento> eventos = new RegistroLista<>();
        Utilidades.recorrer(todos, eventos::agregar);



        //Creacion de los clientes
        Cliente cliente1 = new Cliente("Cliente 1", "111");
        Cliente cliente2 = new Cliente("Cliente 2", "222");
        Cliente cliente3 = new Cliente("Cliente 3", "333");



        //Creacion de los servicios
        Supplier<Pago> proveedor1 = () -> new AdaptadorA(new ProveedorA());
        Supplier<Pago> proveedor2 = () -> new AdaptadorB(new ProveedorB());

        VentaServicio venta1 = new VentaServicio(proveedor1.get());
        VentaServicio venta2 = new VentaServicio(proveedor2.get());
        ReporteServicio reporte1 = new ReporteServicio(eventos);




        int opcion = -1;
        while(opcion != 0){
            System.out.println("-----Menu-----");
            System.out.println("1. Venta en preventa con el proveedor A");
            System.out.println("2. Venta con el proveedor B");
            System.out.println("3. Intentar vender una silla ocupada");
            System.out.println("4. Intentar pasar el limite de boletas");
            System.out.println("5. Acabar la preventa y vender");
            System.out.println("6. Capacidad e ingresos por nivel");
            System.out.println("7. Cancelar con tiempo");
            System.out.println("8. Cancelar a menos de 24 horas");
            System.out.println("9. Ocupacion por evento");
            System.out.println("10. Recaudacion por tipo de evento");
            System.out.println("11. Calendario de proximas funciones");
            System.out.println("12. Buscar un evento");
            System.out.println("13. Localidades por precio");
            System.out.println("0. Salir");

            try{
                opcion = Integer.parseInt(teclado.nextLine());
            }
            catch (NumberFormatException e){
                logger.warning("Eso no es un numero");
                opcion = -1;
            }


            if(opcion == 1){
                System.out.println("-----Venta en preventa con el proveedor A-----");
                try{
                    Boleta boleta1 = venta1.vender(cliente1, concierto1, localidad1, silla1);
                    System.out.println("Precio pagado: " + boleta1.getSilla().getPrecioPagado());
                }
                catch (BoletasException e){
                    logger.severe(e.getMessage());
                }
                finally {
                    System.out.println("Boletas vendidas: " + Utilidades.contar(venta1.getBoletas()));
                }
            }
            else if(opcion == 2){
                System.out.println("-----Venta con el proveedor B-----");
                try{
                    Boleta boleta2 = venta2.vender(cliente2, partido1, localidad4, silla9);
                    System.out.println("Precio pagado: " + boleta2.getSilla().getPrecioPagado());
                }
                catch (BoletasException e){
                    logger.severe(e.getMessage());
                }
            }
            else if(opcion == 3){
                System.out.println("-----Se intenta vender una silla ocupada-----");
                try{
                    venta1.vender(cliente2, concierto1, localidad2, silla7);
                    venta1.vender(cliente3, concierto1, localidad2, silla7);
                }
                catch (SillaOcupadaException e){
                    logger.severe(e.getMessage());
                }
                catch (BoletasException e){
                    logger.severe(e.getMessage());
                }
            }
            else if(opcion == 4){
                System.out.println("-----Se intenta pasar el limite de boletas-----");
                try{
                    venta1.vender(cliente1, concierto1, localidad1, silla2);
                    venta1.vender(cliente1, concierto1, localidad2, silla3);
                    venta1.vender(cliente1, concierto1, localidad2, silla4);
                    venta1.vender(cliente1, concierto1, localidad2, silla5);
                    venta1.vender(cliente1, concierto1, localidad2, silla6);
                }
                catch (LimiteBoletasException e){
                    logger.severe(e.getMessage());
                }
                catch (BoletasException e){
                    logger.severe(e.getMessage());
                }
            }
            else if(opcion == 5){
                System.out.println("-----Se acaba la preventa-----");
                concierto1.setEnPreventa(false);
                try{
                    Boleta boleta3 = venta1.vender(cliente2, concierto1, localidad3, silla8);
                    System.out.println("Precio sin descuento: " + boleta3.getSilla().getPrecioPagado());
                }
                catch (BoletasException e){
                    logger.severe(e.getMessage());
                }
            }
            else if(opcion == 6){
                System.out.println("-----Composite: se le pregunta igual a cualquier nivel-----");
                System.out.println("Silla 1: capacidad " + silla1.capacidad() + ", ingresos " + silla1.ingresos());
                System.out.println("VIP: capacidad " + localidad1.capacidad() + ", ingresos " + localidad1.ingresos());
                System.out.println("Zona 1: capacidad " + zona1.capacidad() + ", ingresos " + zona1.ingresos());
                System.out.println("Escenario: capacidad " + escenario1.capacidad() + ", ingresos " + escenario1.ingresos());
            }
            else if(opcion == 7){
                System.out.println("-----Cancelacion con tiempo-----");
                if(Utilidades.contar(venta1.getBoletas()) == 0){
                    System.out.println("No hay boletas vendidas");
                }
                else{
                    try{
                        Boleta boleta4 = venta1.getBoletas().get(0);
                        venta1.cancelar(boleta4);
                        System.out.println("Boletas vendidas: " + Utilidades.contar(venta1.getBoletas()));
                    }
                    catch (BoletasException e){
                        logger.severe(e.getMessage());
                    }
                }
            }
            else if(opcion == 8){
                System.out.println("-----Cancelacion a menos de 24 horas-----");
                try{
                    Boleta boleta5 = venta1.vender(cliente3, obra1, localidad5, silla11);
                    venta1.cancelar(boleta5);
                }
                catch (CancelacionFueraDeTiempoException e){
                    logger.severe(e.getMessage());
                }
                catch (BoletasException e){
                    logger.severe(e.getMessage());
                }
            }
            else if(opcion == 9){
                System.out.println("-----Ocupacion por evento-----");
                Utilidades.recorrer(reporte1.eventosPorTipoYNombre(), evento -> System.out.println(evento.tipo() + " - " + evento.getNombre() + ": " + reporte1.ocupacion(evento) + "%"));
                System.out.println("Promedio: " + reporte1.ocupacionPromedio() + "%");
            }
            else if(opcion == 10){
                System.out.println("-----Recaudacion por tipo de evento-----");
                Map<String, Double> recaudacion = reporte1.recaudacionPorTipo();
                for(String tipo : recaudacion.keySet()){
                    System.out.println(tipo + ": " + recaudacion.get(tipo));
                }
                System.out.println("Total: " + reporte1.recaudacionTotal());
            }
            else if(opcion == 11){
                System.out.println("-----Calendario de proximas funciones-----");
                Utilidades.recorrer(reporte1.calendario(), evento -> System.out.println(evento.getFecha().toLocalDate() + " - " + evento.getNombre()));
            }
            else if(opcion == 12){
                System.out.println("-----Buscar un evento-----");
                System.out.println("Nombre del evento:");
                Optional<Evento> encontrado = reporte1.buscarEvento(teclado.nextLine());
                if(encontrado.isPresent()){
                    System.out.println("Encontrado: " + encontrado.get().tipo() + " - " + encontrado.get().getNombre());
                }
                else{
                    logger.warning("No se encontro el evento");
                }
            }
            else if(opcion == 13){
                List<Localidad> localidades = new ArrayList<>(zona1.getPartes());

                System.out.println("-----Localidades de la mas barata a la mas cara-----");
                localidades.sort(new ComparadorPorPrecio());
                Utilidades.recorrer(localidades, localidad -> System.out.println(localidad.getNombre() + ": " + localidad.getPrecioBase()));

                System.out.println("-----Localidades de la mas cara a la mas barata-----");
                localidades.sort((a, b) -> Double.compare(b.getPrecioBase(), a.getPrecioBase()));
                Utilidades.recorrer(localidades, localidad -> System.out.println(localidad.getNombre() + ": " + localidad.getPrecioBase()));
            }
            else if(opcion != 0){
                logger.warning("Esa opcion no existe");
            }
        }
    }
}
