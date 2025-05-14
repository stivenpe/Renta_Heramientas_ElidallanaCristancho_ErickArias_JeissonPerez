package com.herramienta.herramienta_app.infrastructure.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herramienta.herramienta_app.domain.entities.Herramienta;

public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
    List<Herramienta> findByDisponibleTrue();
}
