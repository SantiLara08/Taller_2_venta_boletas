package com.boletas;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Registro<T> {
    void agregar(T elemento);
    void eliminar(T elemento);
    List<T> listar();
    Optional<T> buscar(Predicate<T> condicion);
}