package com.boletas;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class VentaServicio {
    private Logger logger;
    private Pago pago;
    private Registro<Boleta> boletas;
    private static final int LIMITE_POR_PERSONA = 4;
    private static final int HORAS_PARA_CANCELAR = 24;

    public VentaServicio(Pago pago){
        this.logger = Logger.getLogger(VentaServicio.class.getName());
        this.pago = pago;
        this.boletas = new RegistroLista<>();
    }

    public List<Boleta> getBoletas(){
        return boletas.listar();
    }

    public Boleta vender(Cliente cliente, Evento evento, Localidad localidad,Silla silla) throws BoletasException{
        if(silla.estaOcupada()){
            logger.warning("La silla " + silla.getNumero() + " ya esta ocupada.");
            throw new SillaOcupadaException("La silla " + silla.getNumero() + " ya esta ocupada.");
        }

        long compradas = boletas.listar().stream()
                .filter(boleta -> boleta.getCliente().getDocumento().equals(cliente.getDocumento()) && boleta.getEvento() == evento)
                .count();

        if (compradas >= LIMITE_POR_PERSONA){
            logger.warning(cliente.getNombre() + " ya tiene el limite de boletas para este evento.");
            throw new LimiteBoletasException(cliente.getNombre() + " ya tiene el limite de boletas para este evento.");
        }

        double precio = localidad.getPrecioBase();

        if(evento.estaEnPreventa()){
            precio = precio * (1-evento.getDescuentoPreventa());
        }

        if(pago.pagar(cliente,precio) == false){
            logger.severe("No se pudo cobrar al cliente " + cliente.getNombre());
            throw new BoletasException("No se pudo cobrar al cliente " + cliente.getNombre());
        }

        silla.vender(precio);

        Boleta boleta = new Boleta(cliente,evento,silla);

        boletas.agregar(boleta);

        logger.info("Boleta vendida a " + cliente.getNombre() + ", silla " + silla.getNumero());

        return boleta;
    }


    public void cancelar(Boleta boleta) throws BoletasException{
        long horas = Duration.between(LocalDateTime.now(),boleta.getEvento().getFecha()).toHours();

        if(horas < HORAS_PARA_CANCELAR){
            logger.warning("Faltan menos de 24 horas, ya no puedes cancelar");
            throw new CancelacionFueraDeTiempoException("Faltan menos de 24 horas, ya no puedes cancelar");
        }else{
            boleta.getSilla().liberar();
            boletas.eliminar(boleta);
            logger.info("La boleta del cliente " + boleta.getCliente().getNombre() + " ha sido cancelada para el evento " + boleta.getEvento().getNombre() + " con la silla " + boleta.getSilla().getNumero());
        }

    }
}
