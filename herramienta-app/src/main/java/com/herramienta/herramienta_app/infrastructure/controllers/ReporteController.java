package com.herramienta.herramienta_app.infrastructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herramienta.herramienta_app.application.services.ReporteService;


@RestController
@RequestMapping("/api/reporte")
public class ReporteController {
     private ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    public void generarReporteDeVentas() {
        reporteService.generarReporteDeVentas();
    }

}
