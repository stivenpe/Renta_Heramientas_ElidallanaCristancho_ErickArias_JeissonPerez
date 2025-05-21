package com.herramienta.herramienta_app.application.services;
import java.util.List;
import java.util.Optional;

import com.herramienta.herramienta_app.domain.entities.Categoria;

public interface CategoriaService {
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    Categoria save(Categoria categoria);
    void deleteById(Long id);
    boolean existsById(Long id);
}
