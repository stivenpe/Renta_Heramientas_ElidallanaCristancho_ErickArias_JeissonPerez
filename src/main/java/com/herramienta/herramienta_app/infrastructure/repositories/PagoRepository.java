package com.herramienta.herramienta_app.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herramienta.herramienta_app.domain.entities.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    Optional<Pago> findByReservaId(Long reservaId);
    List<Pago> findByEstado(String estado);
}
