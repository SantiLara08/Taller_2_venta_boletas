package com.boletas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class RegistroLista<T> implements Registro<T> {
    private List<T> elementos;

    public RegistroLista() {
        this.elementos = new ArrayList<>();
    }

    @Override
    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    @Override
    public void eliminar(T elemento) {
        elementos.remove(elemento);
    }

    @Override
    public List<T> listar() {
        return elementos;
    }

    @Override
    public Optional<T> buscar(Predicate<T> condicion) {
        for (T elemento : elementos) {
            if (condicion.test(elemento)) {
                return Optional.of(elemento);
            }
        }
        return Optional.empty();
    }
}