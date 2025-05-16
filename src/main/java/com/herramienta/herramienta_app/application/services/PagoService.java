package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.domain.dtos.PagoDto;
import com.herramienta.herramienta_app.domain.entities.Pago;
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
    public PagoDto procesarPago(PagoDto pagoDto) {
        Reserva reserva = reservaRepository.findById(pagoDto.getReservaId())
            .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

        if (!"PENDIENTE".equals(reserva.getEstado())) {
            throw new PagoFallidoException("La reserva no está pendiente de pago");
        }

        Pago pago = new Pago();
        pago.setEstado("COMPLETADO");
        pago.setFechaPago(LocalDateTime.now());
        pago.setMetodoPago(pagoDto.getMetodoPago());
        pago.setMonto(pagoDto.getMonto());
        pago.setReserva(reserva);

        Pago saved = pagoRepository.save(pago);

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

    private PagoDto mapToDTO(Pago pago) {
        PagoDto dto = new PagoDto();
        dto.setId(pago.getId());
        dto.setEstado(pago.getEstado());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setMonto(pago.getMonto());
        dto.setReservaId(pago.getReserva() != null ? pago.getReserva().getId() : null);
        return dto;
    }
}
