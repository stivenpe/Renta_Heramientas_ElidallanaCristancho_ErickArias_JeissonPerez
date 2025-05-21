package com.herramienta.herramienta_app.application.services;

import java.util.List;
import java.util.Optional;

import com.herramienta.herramienta_app.domain.entities.Herramienta;

public interface HerramientaService {
    List<Herramienta> findAll();
    Optional<Herramienta> findById(Long id);
    Herramienta save(Herramienta herramienta);
    void deleteById(Long id);
    boolean existsById(Long id);
}