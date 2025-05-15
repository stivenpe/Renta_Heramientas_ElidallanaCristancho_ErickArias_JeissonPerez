package com.herramienta.herramienta_app.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.herramienta.herramienta_app.application.PagoService;
import com.herramienta.herramienta_app.domain.entities.Pago;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public void procesarPago(@RequestBody Pago pago) {
        pagoService.procesarPago(pago);
    }
}
