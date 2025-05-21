package com.herramienta.herramienta_app.application.services;

import java.util.List;
import java.util.Optional;

import com.herramienta.herramienta_app.domain.entities.Notificacion;

public interface NotificacionService {
    List<Notificacion> findAll();
    Optional<Notificacion> findById(Long id);
    Notificacion save(Notificacion notificacion);
    void deleteById(Long id);
}