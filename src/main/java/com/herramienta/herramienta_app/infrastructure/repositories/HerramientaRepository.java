package com.herramienta.herramienta_app.infrastructure.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.herramienta.herramienta_app.domain.entities.Herramienta;

public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
    List<Herramienta> findByDisponibleTrue();
    List<Herramienta> findHerramientasNoDevueltasPorUsuario(@Param("idCliente") Long idCliente);

}
