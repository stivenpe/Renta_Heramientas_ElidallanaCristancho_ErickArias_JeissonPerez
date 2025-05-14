package com.herramienta.herramienta_app.infrastructure.controllers;

import com.herramienta.herramienta_app.application.services.PagoService;
import com.herramienta.herramienta_app.domain.entities.Pago;

public class PagoController {
     private PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    public void procesarPago(Pago pago) {
        pagoService.procesarPago(pago);
    }

}
