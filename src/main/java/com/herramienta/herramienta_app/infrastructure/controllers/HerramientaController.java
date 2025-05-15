package com.herramienta.herramienta_app.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.herramienta.herramienta_app.application.services.HerramientaService;
import com.herramienta.herramienta_app.domain.dtos.HerramientaDto;
import com.herramienta.herramienta_app.domain.entities.Herramienta;

@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {

    @Autowired
    private HerramientaService herramientaService;

    @PostMapping
    public ResponseEntity<Herramienta> crearHerramienta(@ModelAttribute HerramientaDto dto) {
        Herramienta herramienta = herramientaService.crearHerramienta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(herramienta);
    }

    @GetMapping
    public ResponseEntity<List<Herramienta>> obtenerTodas() {
        return ResponseEntity.ok(herramientaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Herramienta> obtenerPorId(@PathVariable Long id) {
        Herramienta herramienta = herramientaService.obtenerPorId(id);
        return ResponseEntity.ok(herramienta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Herramienta> actualizar(@PathVariable Long id, @RequestBody HerramientaDto dto) {
        Herramienta herramienta = herramientaService.actualizarHerramienta(id, dto);
        return ResponseEntity.ok(herramienta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        herramientaService.eliminarHerramienta(id);
        return ResponseEntity.noContent().build();
    }
}
