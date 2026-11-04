package com.boletas;
import java.util.Comparator;

public class ComparadorPorPrecio implements Comparator<Localidad>{
    @Override
    public int compare(Localidad a, Localidad b){
        return Double.compare(a.getPrecioBase(),b.getPrecioBase());
    }
}
