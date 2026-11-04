package com.boletas;

public class AdaptadorA implements Pago {

    private ProveedorA proveedor;

    public AdaptadorA(ProveedorA proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public boolean pagar(Cliente cliente, double pesos) {
        return proveedor.procesarPago(cliente.getDocumento(), pesos);
    }
}
