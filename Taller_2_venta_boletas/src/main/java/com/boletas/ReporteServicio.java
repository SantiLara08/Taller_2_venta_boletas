package com.boletas;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;


public class ReporteServicio {
    private Logger logger;
    private Registro<Evento> eventos;

    public ReporteServicio(Registro<Evento> eventos){
        this.logger = Logger.getLogger(ReporteServicio.class.getName());
        this.eventos = eventos;
    }

    public Optional<Evento> buscarEvento(String nombre){
        return eventos.buscar(e -> e.getNombre().equals(nombre));
    }

    public double ocupacion(Evento evento){
        Escenario escenario = evento.getEscenario();
        if(escenario.capacidad() == 0){
            logger.warning("El evento " + evento.getNombre() + " no tiene sillas");
            return 0;
        } else{
            return (double) escenario.ocupadas() / escenario.capacidad() * 100;
        }
    }

    public List<Evento> calendario(){
        List<Evento> proximos = new ArrayList<>(eventos.listar().stream()
                .filter(e -> e.getFecha().isAfter(LocalDateTime.now()))
                .toList());
        Utilidades.ordenar(proximos);
        return proximos;
    }

    public Map<String, Double> recaudacionPorTipo(){
        Function<Evento, Escenario> escenarioDe = Evento::getEscenario;
        Map<String, Double> recaudacion = new HashMap<>();
        for(Evento evento : eventos.listar()){
            String tipo = evento.tipo();
            if(recaudacion.containsKey(tipo) == false){
                List<Escenario> escenarios = eventos.listar().stream()
                        .filter(e -> e.tipo().equals(tipo))
                        .map(escenarioDe)
                        .toList();
                recaudacion.put(tipo, Utilidades.sumarIngresos(escenarios));
            }
        }
        logger.info("Se calculo la recaudacion por tipo de evento");
        return recaudacion;
    }

    public double recaudacionTotal(){
        return eventos.listar().stream()
                .map(Evento::getEscenario)
                .map(Escenario::ingresos)
                .reduce(0.0, Double::sum);
    }

    public double ocupacionPromedio(){
        return eventos.listar().stream()
                .mapToDouble(this::ocupacion)
                .average()
                .orElse(0);
    }

    public List<Evento> eventosPorTipoYNombre(){
        return eventos.listar().stream()
                .sorted(Comparator.comparing(Evento::tipo).thenComparing(Evento::getNombre).reversed())
                .toList();
    }
}