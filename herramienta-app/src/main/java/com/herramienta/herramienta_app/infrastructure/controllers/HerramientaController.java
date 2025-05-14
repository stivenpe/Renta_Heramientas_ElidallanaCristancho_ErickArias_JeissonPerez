package com.herramienta.herramienta_app.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.herramienta.herramienta_app.application.services.HerramientaService;
import com.herramienta.herramienta_app.domain.dtos.HerramientaDto;
import com.herramienta.herramienta_app.domain.entities.Herramienta;

@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {

    @Autowired
    private HerramientaService herramientaService;

    @PostMapping
    public ResponseEntity<Herramienta> crearHerramienta(@ModelAttribute HerramientaDto dto,
                                                        @RequestParam("imagen") MultipartFile imagen) {
        Herramienta herramienta = herramientaService.crearHerramienta(dto, imagen);
        return ResponseEntity.status(HttpStatus.CREATED).body(herramienta);
    }

    // Endpoints para leer, actualizar y eliminar herramientas
}
