package com.herramienta.herramienta_app.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.herramienta.herramienta_app.application.services.NotificacionService;
import com.herramienta.herramienta_app.domain.entities.Notificacion;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    @Autowired
    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping
    public void enviarNotificacion(@RequestBody Notificacion notificacion) {
        notificacionService.enviarNotificacion(notificacion);
    }

    @PostMapping("/recordatorio/{idCliente}")
    public void enviarRecordatorio(@PathVariable Long idCliente) {
        notificacionService.enviarRecordatorioDeDevolucion(idCliente);
    }
}
