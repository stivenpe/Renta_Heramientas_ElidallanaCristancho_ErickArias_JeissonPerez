package com.herramienta.herramienta_app.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.herramienta.herramienta_app.domain.entities.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByFechaPagoBetween(LocalDateTime inicio, LocalDateTime fin);
}

