package com.herramienta.herramienta_app.infrastructure.repositories.Proveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import com.herramienta.herramienta_app.domain.entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {}