package com.herramienta.herramienta_app.infrastructure.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.herramienta.herramienta_app.application.services.NotificacionService;
import com.herramienta.herramienta_app.domain.entities.Notificacion;
import com.herramienta.herramienta_app.domain.entities.Usuario;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping("/todas")
    public ResponseEntity<List<Notificacion>> listarTodasNotificaciones() {
        return ResponseEntity.ok(notificacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerNotificacion(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@RequestBody Notificacion notificacion) {
        return ResponseEntity.status(201).body(notificacionService.save(notificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Long id) {
        notificacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}