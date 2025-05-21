package com.herramienta.herramienta_app.application.services;

import java.util.List;
import java.util.Optional;
import com.herramienta.herramienta_app.domain.entities.Reserva;

public interface ReservaService {
    List<Reserva> findAll();
    Optional<Reserva> findById(Long id);
    Reserva save(Reserva reserva);
    void deleteById(Long id);
}