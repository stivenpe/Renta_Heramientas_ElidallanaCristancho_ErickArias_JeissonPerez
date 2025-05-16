package com.herramienta.herramienta_app.infrastructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herramienta.herramienta_app.application.services.ReservaService;
import com.herramienta.herramienta_app.domain.dtos.ReservaDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;
    
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO created = reservaService.crearReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/proveedor/{proveedorId}")
    @PreAuthorize("hasRole('PROVEEDOR')")
    public ResponseEntity<List<ReservaDTO>> listarReservasPorProveedor(@PathVariable Long proveedorId) {
        List<ReservaDTO> reservas = reservaService.listarPorProveedor(proveedorId);
        return ResponseEntity.ok(reservas);
    }
    
}

