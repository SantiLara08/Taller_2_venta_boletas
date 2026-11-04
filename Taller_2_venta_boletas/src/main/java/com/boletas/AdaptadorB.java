 package com.boletas;

public class AdaptadorB implements Pago {

    private ProveedorB proveedor;

    public AdaptadorB(ProveedorB proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public boolean pagar(Cliente cliente, double pesos) {
        return proveedor.cobrar(String.valueOf(pesos));
    }
}
