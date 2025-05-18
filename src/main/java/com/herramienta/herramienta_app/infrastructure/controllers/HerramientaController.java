package com.herramienta.herramienta_app.infrastructure.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.herramienta.herramienta_app.application.services.HerramientaService;
import com.herramienta.herramienta_app.domain.entities.Herramienta;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/herramientas")
@RequiredArgsConstructor
public class HerramientaController {

    private final HerramientaService herramientaService;

    @GetMapping
    public ResponseEntity<List<Herramienta>> listarHerramientasDisponibles() {
        return ResponseEntity.ok(herramientaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Herramienta> obtenerHerramienta(@PathVariable Long id) {
        return ResponseEntity.ok(herramientaService.findById(id).orElseThrow());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PROVEEDOR', 'ADMIN')")
    public ResponseEntity<Herramienta> crearHerramienta(@RequestBody Herramienta herramienta) {
        Herramienta created = herramientaService.save(herramienta);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROVEEDOR', 'ADMIN')")
    public ResponseEntity<Herramienta> update(@PathVariable Long id, @RequestBody Herramienta herramienta) {
        if (!herramientaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        herramienta.setId(id);
        Herramienta actualizarHerramienta = herramientaService.save(herramienta);
        return ResponseEntity.ok(actualizarHerramienta);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROVEEDOR', 'ADMIN')")
    public ResponseEntity<Void> eliminarHerramienta(@PathVariable Long id) {
        herramientaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}