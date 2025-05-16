package com.herramienta.herramienta_app.infrastructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herramienta.herramienta_app.application.services.HerramientaService;
import com.herramienta.herramienta_app.domain.dtos.HerramientaDTO;
import com.herramienta.herramienta_app.domain.entities.Usuario;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/herramientas")
@RequiredArgsConstructor
public class HerramientaController {
    private final HerramientaService herramientaService;
    
    @GetMapping
    public ResponseEntity<List<HerramientaDTO>> listarHerramientasDisponibles() {
        List<HerramientaDTO> herramientas = herramientaService.buscarHerramientasDisponibles();
        return ResponseEntity.ok(herramientas);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('PROVEEDOR')")
    public ResponseEntity<HerramientaDTO> crearHerramienta(
            @RequestBody HerramientaDTO herramientaDTO,
            @AuthenticationPrincipal Usuario usuario) {
        HerramientaDTO created = herramientaService.crearHerramienta(herramientaDTO, usuario.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
}
