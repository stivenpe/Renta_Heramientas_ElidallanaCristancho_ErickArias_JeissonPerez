package com.herramienta.herramienta_app.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herramienta.herramienta_app.domain.entities.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuario_Id(Long idUsuario); // Asumiendo que Notificacion tiene un campo 'usuario' de tipo Usuario
}
