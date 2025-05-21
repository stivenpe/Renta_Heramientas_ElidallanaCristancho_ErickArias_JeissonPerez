package com.herramienta.herramienta_app.infrastructure.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.herramienta.herramienta_app.application.services.ProveedorService;
import com.herramienta.herramienta_app.domain.entities.Proveedor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consproveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> listarTodos() {
        return ResponseEntity.ok(proveedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor) {
        return ResponseEntity.status(201).body(proveedorService.save(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        proveedor.setId(id);
        return ResponseEntity.ok(proveedorService.save(proveedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}