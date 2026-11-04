package com.boletas;
import java.util.List;
import java.util.function.Consumer;


public class Utilidades {
    private Utilidades(){}

    public static int contar(List<?> lista){
        return lista.size();
    }

    public static double sumarIngresos(List<? extends ParteEscenario> partes){
        double total = 0;
        for(ParteEscenario parte : partes){
            total = total + parte.ingresos();
        }
        return total;
    }

    public static <T> void agregarTodos(List<? super T> destino,List<T> origen){
        for(T elemento:origen){
            destino.add(elemento);
        }
    }

    public static <T> void recorrer(List<T> lista, Consumer<T> accion){
        for(T elemento : lista){
            accion.accept(elemento);
        }
    }

    public static <T extends Comparable<? super T>> void ordenar(List<T> lista){
        for(int i = 0;i<lista.size() -1;i++){
            for(int j = 0; j < lista.size() -1 - i;j++){
                if (lista.get(j).compareTo(lista.get(j+1))>0){
                    T temporal = lista.get(j);
                    lista.set(j,lista.get(j+1));
                    lista.set(j + 1, temporal);
                }
            }
        }
    }



}
