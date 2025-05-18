package com.herramienta.herramienta_app.application.services;

import java.util.List;
import java.util.Optional;

import com.herramienta.herramienta_app.domain.entities.Pago;

public interface PagoService {
    List<Pago> findAll();
    Optional<Pago> findById(Long id);
    Pago save(Pago pago);
    void deleteById(Long id);
}