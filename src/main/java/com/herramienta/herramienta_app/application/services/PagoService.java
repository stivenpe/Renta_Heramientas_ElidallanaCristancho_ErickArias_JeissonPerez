package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.domain.entities.Reserva;
import com.herramienta.herramienta_app.domain.exceptions.PagoFallidoException;
import com.herramienta.herramienta_app.infrastructure.repositories.PagoRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.ReservaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;
    private final FacturaService facturaService;
    private final NotificacionService notificacionService;

    @Transactional
    public com.herramienta.herramienta_app.domain.dtos.Pago procesarPago(com.herramienta.herramienta_app.domain.dtos.Pago pagoDto) {
        Reserva reserva = reservaRepository.findById(pagoDto.getReservaId())
            .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

        if (!"PENDIENTE".equals(reserva.getEstado())) {
            throw new PagoFallidoException("La reserva no está pendiente de pago");
        }

        com.herramienta.herramienta_app.domain.entities.Pago pagoEntity = new com.herramienta.herramienta_app.domain.entities.Pago();
        pagoEntity.setEstado("COMPLETADO");
        pagoEntity.setFechaPago(LocalDateTime.now());
        pagoEntity.setMetodoPago(pagoDto.getMetodoPago());
        pagoEntity.setMonto(pagoDto.getMonto());
        pagoEntity.setReserva(reserva);

        com.herramienta.herramienta_app.domain.entities.Pago saved = pagoRepository.save(pagoEntity);

        reserva.setEstado("APROBADA");
        reservaRepository.save(reserva);

        facturaService.generarFactura(reserva);

        notificacionService.crearNotificacion(
            reserva.getCliente().getId(),
            "Pago completado",
            "Tu pago por la reserva #" + reserva.getId() + " ha sido procesado"
        );

        notificacionService.crearNotificacion(
            reserva.getProveedor().getId(),
            "Pago recibido",
            "Has recibido un pago por la reserva #" + reserva.getId()
        );

        return mapToDTO(saved);
    }

    private com.herramienta.herramienta_app.domain.dtos.Pago mapToDTO(com.herramienta.herramienta_app.domain.entities.Pago pagoEntity) {
        com.herramienta.herramienta_app.domain.dtos.Pago dto = new com.herramienta.herramienta_app.domain.dtos.Pago();
        dto.setId(pagoEntity.getId());
        dto.setEstado(pagoEntity.getEstado());
        dto.setMetodoPago(pagoEntity.getMetodoPago());
        dto.setMonto(pagoEntity.getMonto());
        dto.setReservaId(pagoEntity.getReserva() != null ? pagoEntity.getReserva().getId() : null);
        return dto;
    }
}