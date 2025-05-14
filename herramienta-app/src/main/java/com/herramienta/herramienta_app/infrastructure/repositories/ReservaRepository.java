package com.herramienta.herramienta_app.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herramienta.herramienta_app.domain.entities.Reserva;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByClienteId(Long clienteId);
    List<Reserva> findByHerramientaId(Long herramientaId);
}
