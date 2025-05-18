package com.herramienta.herramienta_app.infrastructure.repositories.Rol;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herramienta.herramienta_app.domain.entities.Rol;


public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}