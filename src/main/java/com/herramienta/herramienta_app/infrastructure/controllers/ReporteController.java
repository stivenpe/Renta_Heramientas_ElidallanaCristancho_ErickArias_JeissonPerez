package com.herramienta.herramienta_app.infrastructure.controllers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.herramienta.herramienta_app.application.services.ReporteService;
import com.herramienta.herramienta_app.domain.dtos.ReporteDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reportes")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ReporteController {
    private final ReporteService reporteService;
    
    @GetMapping("/ingresos")
    public ResponseEntity<ReporteDTO> generarReporteIngresos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        ReporteDTO reporte = reporteService.generarReporteIngresos(inicio, fin);
        return ResponseEntity.ok(reporte);
    }
}
