package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.infrastructure.repositories.PagoRepository;

public class ReporteService {
     private ReservaRepository reservaRepository;
    private PagoRepository pagoRepository;

    public ReporteService(ReservaRepository reservaRepository, PagoRepository pagoRepository) {
        this.reservaRepository = reservaRepository;
        this.pagoRepository = pagoRepository;
    }

    public void generarReporteDeVentas() {
      
    }

    public void generarReporteDeAlquileres() {
        // Lógica para generar reporte de alquileres
    }

    public void generarEstadisticasDeUso() {
        // Estadísticas de uso de herramientas y clientes frecuentes
    }

}
