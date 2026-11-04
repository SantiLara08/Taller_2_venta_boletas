package com.boletas;
import java.util.ArrayList;
import java.util.List;

public abstract class Contenedor<T extends ParteEscenario> implements ParteEscenario{
    private String nombre;
    private List<T> partes;

    public Contenedor(String nombre){
        this.nombre = nombre;
        this.partes = new ArrayList<>();
    }

    public void agregar(T parte){
        partes.add(parte);
    }

    @Override
    public int capacidad(){
        int total = 0;
        for(T parte : partes){
            total = total + parte.capacidad();
        }
        return total;
    }

    @Override
    public double ingresos(){
        double total = 0;
        for(T parte : partes){
            total = total + parte.ingresos();
        }
        return total;
    }

    @Override
    public int ocupadas(){
        int total = 0;
        for(T parte:partes){
            total = total + parte.ocupadas();
        }
        return total;
    }

    public String getNombre() {
        return nombre;
    }

    public List<T> getPartes() {
        return partes;
    }
}
