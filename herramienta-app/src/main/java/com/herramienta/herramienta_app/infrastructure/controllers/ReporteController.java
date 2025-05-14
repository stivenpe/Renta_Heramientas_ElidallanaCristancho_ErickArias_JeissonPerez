package com.herramienta.herramienta_app.infrastructure.controllers;

import com.herramienta.herramienta_app.application.services.ReporteService;

public class ReporteController {
     private ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    public void generarReporteDeVentas() {
        reporteService.generarReporteDeVentas();
    }

}
