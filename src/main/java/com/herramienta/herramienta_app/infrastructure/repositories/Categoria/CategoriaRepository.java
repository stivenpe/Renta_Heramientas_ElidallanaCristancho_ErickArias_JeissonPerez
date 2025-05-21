package com.herramienta.herramienta_app.infrastructure.repositories.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import com.herramienta.herramienta_app.domain.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre);
}