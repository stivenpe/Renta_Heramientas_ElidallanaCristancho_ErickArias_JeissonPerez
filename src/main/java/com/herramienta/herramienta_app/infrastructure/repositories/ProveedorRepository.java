package com.herramienta.herramienta_app.infrastructure.repositories;


import com.herramienta.herramienta_app.domain.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}