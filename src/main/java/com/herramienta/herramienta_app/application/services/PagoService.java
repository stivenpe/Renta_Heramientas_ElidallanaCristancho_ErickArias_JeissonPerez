package com.herramienta.herramienta_app.application.services;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.entities.Pago;
import com.herramienta.herramienta_app.infrastructure.repositories.PagoRepository;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;
    private final FacturaService facturaService;
    private final NotificacionService notificacionService;
    
    @Transactional
    public PagoDTO procesarPago(PagoDTO pagoDTO) {
        Reserva reserva = reservaRepository.findById(pagoDTO.getReservaId())
            .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
            
        if (!"PENDIENTE".equals(reserva.getEstado())) {
            throw new PagoFallidoException("La reserva no está pendiente de pago");
        }
        
        Pago pago = new Pago();
        // Mapear DTO a entidad
        pago.setEstado("COMPLETADO");
        pago.setFechaPago(LocalDateTime.now());
        
        Pago saved = pagoRepository.save(pago);
        
        // Actualizar estado de la reserva
        reserva.setEstado("APROBADA");
        reservaRepository.save(reserva);
        
        // Generar factura
        facturaService.generarFactura(reserva);
        
        // Notificar al cliente y proveedor
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
}