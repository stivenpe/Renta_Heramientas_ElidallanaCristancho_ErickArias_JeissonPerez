package com.herramienta.herramienta_app.infrastructure.repositories.Pago;

import org.springframework.data.jpa.repository.JpaRepository;
import com.herramienta.herramienta_app.domain.entities.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {}