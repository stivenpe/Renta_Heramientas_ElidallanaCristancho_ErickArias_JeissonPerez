package com.herramienta.herramienta_app.application.services;

import java.util.List;
import java.util.Optional;
import com.herramienta.herramienta_app.domain.entities.Factura;

public interface FacturaService {
    List<Factura> findAll();
    Optional<Factura> findById(Long id);
    Factura save(Factura factura);
    void deleteById(Long id);
    boolean existsById(Long id);
    
}
