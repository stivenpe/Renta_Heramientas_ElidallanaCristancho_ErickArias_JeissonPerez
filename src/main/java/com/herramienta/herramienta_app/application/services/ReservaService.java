package com.herramienta.herramienta_app.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.ReservaDto;
import com.herramienta.herramienta_app.domain.entities.Cliente;
import com.herramienta.herramienta_app.domain.entities.Herramienta;
import com.herramienta.herramienta_app.domain.entities.Reserva;
import com.herramienta.herramienta_app.infrastructure.repositories.ClienteRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.HerramientaRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HerramientaRepository herramientaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Reserva crearReserva(ReservaDto dto) {
        Herramienta herramienta = herramientaRepository.findById(dto.getIdHerramienta())
                .orElseThrow(() -> new RuntimeException("Herramienta no encontrada"));

        if (!herramienta.getDisponible()) {
            throw new RuntimeException("La herramienta no está disponible");

        }

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Reserva reserva = new Reserva();
        reserva.setHerramienta(herramienta);
        reserva.setCliente(cliente);
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFin(dto.getFechaFin());
        reserva.setTotal(dto.getTotal());
        reserva.setEstado("Confirmada");

        herramienta.setDisponible(false);
        herramientaRepository.save(herramienta);

        return reservaRepository.save(reserva);
    }

    public void cancelarReserva(Long idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
        .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

                

        reserva.setEstado("Cancelada");
        reservaRepository.save(reserva);

        Herramienta herramienta = reserva.getHerramienta();
        herramienta.setDisponible(true);
        herramientaRepository.save(herramienta);
    }

    // Métodos para obtener historial de reservas
}

