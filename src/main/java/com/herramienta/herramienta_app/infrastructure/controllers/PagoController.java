package com.herramienta.herramienta_app.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herramienta.herramienta_app.application.services.PagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;
    
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PagoDto> procesarPago(@RequestBody PagoDto pagoDTO) {
        PagoDto processed = pagoService.procesarPago(pagoDTO);
        return ResponseEntity.ok(processed);
    }
}