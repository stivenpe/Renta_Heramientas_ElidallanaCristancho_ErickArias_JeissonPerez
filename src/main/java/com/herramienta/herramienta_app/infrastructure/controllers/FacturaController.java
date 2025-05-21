package com.herramienta.herramienta_app.infrastructure.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.herramienta.herramienta_app.application.services.FacturaService;
import com.herramienta.herramienta_app.domain.entities.Factura;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Factura>> listarTodasFacturas() {
        return ResponseEntity.ok(facturaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.findById(id).orElseThrow());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        return ResponseEntity.status(201).body(facturaService.save(factura));
    }

    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<Factura> obtenerPorReservaId(@PathVariable Long reservaId) {
        return ResponseEntity.ok(facturaService.findById(reservaId).orElseThrow());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        facturaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}