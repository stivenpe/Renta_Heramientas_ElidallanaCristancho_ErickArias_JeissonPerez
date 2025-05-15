package com.herramienta.herramienta_app.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.herramienta.herramienta_app.domain.entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    // Puedes agregar métodos personalizados si los necesitas
}
