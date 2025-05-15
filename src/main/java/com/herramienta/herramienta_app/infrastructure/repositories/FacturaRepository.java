package com.herramienta.herramienta_app.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herramienta.herramienta_app.domain.entities.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Optional<Factura> findByReservaId(Long reservaId);
    List<Factura> findByReservaClienteId(Long clienteId);
    List<Factura> findByReservaProveedorId(Long proveedorId);
}
