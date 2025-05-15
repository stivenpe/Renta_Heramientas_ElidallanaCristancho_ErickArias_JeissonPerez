package com.herramienta.herramienta_app.infrastructure.controllers;

import com.herramienta.herramienta_app.domain.entities.Pago;

public class facturaContrller {
      private Factura Factura;

    public facturaContrller(Factura Factura) {
        this.Factura = Factura;
    }

    public void procesarFactura(Pago pago) {
        factura.procesarFactura(pago);
    }


}
