package com.herramienta.herramienta_app.infrastructure.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herramienta.herramienta_app.application.services.NotificacionService;
import com.herramienta.herramienta_app.domain.entities.Notificacion;
import com.herramienta.herramienta_app.domain.entities.Usuario;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService notificacionService;
    
    @GetMapping
    public ResponseEntity<List<Notificacion>> listarNotificacionesNoLeidas(@AuthenticationPrincipal Usuario usuario) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesNoLeidas(usuario.getId());
        return ResponseEntity.ok(notificaciones);
    }
    
    @PutMapping("/{id}/leer")
    public ResponseEntity<Void> marcarComoLeida(@PathVariable Long id) {
        notificacionService.marcarComoLeida(id);
        return ResponseEntity.noContent().build();
    }
}
