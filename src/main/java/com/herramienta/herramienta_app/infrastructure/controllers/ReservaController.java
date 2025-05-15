package com.herramienta.herramienta_app.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herramienta.herramienta_app.application.services.ReservaService;
import com.herramienta.herramienta_app.domain.dtos.ReservaDto;
import com.herramienta.herramienta_app.domain.entities.Reserva;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaDto dto) {
        Reserva reserva = reservaService.crearReserva(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints para obtener historial de reservas
}

