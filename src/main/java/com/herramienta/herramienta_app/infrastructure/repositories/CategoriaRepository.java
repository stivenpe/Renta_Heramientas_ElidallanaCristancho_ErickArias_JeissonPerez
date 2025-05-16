package com.herramienta.herramienta_app.infrastructure.repositories;


import com.herramienta.herramienta_app.domain.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombre(String nombre);
}
