package com.herramienta.herramienta_app.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.herramienta.herramienta_app.application.services.FacturaService;
import com.herramienta.herramienta_app.domain.dtos.FacturaDTO;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FacturaDTO>> listarTodasFacturas() {
        List<FacturaDTO> facturas = facturaService.listarTodas();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerFacturaPorId(@PathVariable Long id) {
        FacturaDTO factura = facturaService.obtenerPorId(id);
        return ResponseEntity.ok(factura);
    }

    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<FacturaDTO> obtenerFacturaPorReserva(@PathVariable Long reservaId) {
        FacturaDTO factura = facturaService.obtenerPorReservaId(reservaId);
        return ResponseEntity.ok(factura);
    }

    @GetMapping("/cliente/{clienteId}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<List<FacturaDTO>> listarFacturasPorCliente(@PathVariable Long clienteId) {
        List<FacturaDTO> facturas = facturaService.listarPorClienteId(clienteId);
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/proveedor/{proveedorId}")
    @PreAuthorize("hasRole('PROVEEDOR')")
    public ResponseEntity<List<FacturaDTO>> listarFacturasPorProveedor(@PathVariable Long proveedorId) {
        List<FacturaDTO> facturas = facturaService.listarPorProveedorId(proveedorId);
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}/descargar-pdf")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable Long id) {
        byte[] pdf = facturaService.descargarPdf(id);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=\"factura_" + id + ".pdf\"")
                .body(pdf);
    }

    @GetMapping("/{id}/descargar-xml")
    public ResponseEntity<byte[]> descargarXml(@PathVariable Long id) {
        byte[] xml = facturaService.descargarXml(id);
        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .header("Content-Disposition", "attachment; filename=\"factura_" + id + ".xml\"")
                .body(xml);
    }
}


