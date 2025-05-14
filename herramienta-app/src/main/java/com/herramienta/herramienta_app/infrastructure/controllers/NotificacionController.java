package com.herramienta.herramienta_app.infrastructure.controllers;

import com.herramienta.herramienta_app.application.services.NotificacionService;
import com.herramienta.herramienta_app.domain.entities.Notificacion;

public class NotificacionController {
     private NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        notificacionService.enviarNotificacion(notificacion);
    }

}
