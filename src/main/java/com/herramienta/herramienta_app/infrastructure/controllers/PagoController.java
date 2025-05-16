package com.herramienta.herramienta_app.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herramienta.herramienta_app.application.services.PagoService;
import com.herramienta.herramienta_app.domain.dtos.PagoDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;
    
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PagoDTO> procesarPago(@RequestBody PagoDTO pagoDTO) {
        PagoDTO processed = pagoService.procesarPago(pagoDTO);
        return ResponseEntity.ok(processed);
    }
}