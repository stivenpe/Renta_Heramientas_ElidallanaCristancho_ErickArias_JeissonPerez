package com.herramienta.herramienta_app.application.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.ReporteDto;
import com.herramienta.herramienta_app.domain.entities.Pago;
import com.herramienta.herramienta_app.infrastructure.repositories.HerramientaRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.PagoRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.ReservaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {
    private final ReservaRepository reservaRepository;
    private final HerramientaRepository herramientaRepository;
    private final PagoRepository pagoRepository;
    
    public ReporteDto generarReporteIngresos(LocalDate inicio, LocalDate fin) {
        List<Pago> pagos = pagoRepository.findByFechaPagoBetween(
            inicio.atStartOfDay(),
            fin.atTime(23, 59, 59)
        );
        
        double total = pagos.stream()
            .mapToDouble(Pago::getMonto)
            .sum();
            
        ReporteDto reporte = new ReporteDto();
        reporte.setTipo("INGRESOS");
        reporte.setFechaInicio(inicio);
        reporte.setFechaFin(fin);
        reporte.setDatos(Map.of("total", total, "pagos", pagos.size()));
        
        return reporte;
    }
    
    // Otros métodos de reporte
}