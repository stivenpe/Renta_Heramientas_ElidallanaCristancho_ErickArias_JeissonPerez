package com.herramienta.herramienta_app.infrastructure.repositories;

import java.util.List;

import com.herramienta.herramienta_app.domain.entities.Notificacion;

public interface NotificacionRepository {
     void save(Notificacion notificacion);
    List<Notificacion> findByUsuario(Long idUsuario);
    void update(Notificacion notificacion);
    void delete(Long idNotificacion);

}
