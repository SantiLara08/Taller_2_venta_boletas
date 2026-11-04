package com.boletas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroListaTest {

    private RegistroLista<String> registro;

    @BeforeEach
    public void preparar() {
        registro = new RegistroLista<>();
    }

    @Test
    public void agregarDosElementos() {
        registro.agregar("a");
        registro.agregar("b");
        assertEquals(2, registro.listar().size());
    }

    @Test
    public void buscarAlgoQueNoEstaDevuelveVacio() {
        registro.agregar("a");
        Optional<String> resultado = registro.buscar(e -> e.equals("z"));
        assertTrue(resultado.isEmpty());
    }
}