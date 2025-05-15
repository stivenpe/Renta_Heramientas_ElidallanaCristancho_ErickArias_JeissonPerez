package com.herramienta.herramienta_app.infrastructure.controllers;


import com.herramienta.herramienta_app.domain.entities.Factura;
import com.herramienta.herramienta_app.domain.entities.Pago;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final Factura factura;

    public FacturaController(Factura factura) {
        this.factura = factura;
    }

    @PostMapping("/procesar")
    public String procesarFactura(@RequestBody Pago pago) {
        factura.procesarFactura(pago);
        return "Factura procesada correctamente";
    }
}


