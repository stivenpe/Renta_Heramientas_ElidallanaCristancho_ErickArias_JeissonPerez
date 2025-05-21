package com.herramienta.herramienta_app.infrastructure.repositories.Notificacion;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.herramienta.herramienta_app.domain.entities.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuarioIdAndLeidaFalse(Long usuarioId);
}