package com.herramienta.herramienta_app.application.services;

import java.util.List;
import java.util.Optional;

import com.herramienta.herramienta_app.domain.entities.Proveedor;

public interface ProveedorService {
    List<Proveedor> findAll();
    Optional<Proveedor> findById(Long id);
    Proveedor save(Proveedor proveedor);
    void deleteById(Long id);
    boolean existsById(Long id);
    
}
