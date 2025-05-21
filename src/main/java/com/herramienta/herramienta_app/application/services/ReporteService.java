package com.herramienta.herramienta_app.application.services;

import java.util.List;
import java.util.Optional;

import com.herramienta.herramienta_app.domain.dtos.Reporte;

public interface ReporteService {
    List<Reporte> findAll();
    Optional<Reporte> findById(Long id);
    Reporte save(Reporte reporte);
    void deleteById(Long id);
    boolean existsById(Long id);
}