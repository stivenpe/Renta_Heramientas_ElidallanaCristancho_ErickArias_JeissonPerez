package com.herramienta.herramienta_app.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.ReservaDto;
import com.herramienta.herramienta_app.domain.entities.Cliente;
import com.herramienta.herramienta_app.domain.entities.Herramienta;
import com.herramienta.herramienta_app.domain.entities.Reserva;
import com.herramienta.herramienta_app.domain.entities.Usuario;
import com.herramienta.herramienta_app.infrastructure.repositories.HerramientaRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.ReservaRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final HerramientaRepository herramientaRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificacionService notificacionService;
    
    public ReservaDto crearReserva(ReservaDto ReservaDto) {
        Herramienta herramienta = herramientaRepository.findById(ReservaDto.getHerramientaId())
            .orElseThrow(() -> new HerramientaNoDisponibleException("Herramienta no encontrada"));
            
        if (herramienta.getCantidadDisponible() <= 0) {
            throw new HerramientaNoDisponibleException("Herramienta no disponible");
        }
        
        Usuario cliente = usuarioRepository.findById(ReservaDto.getClienteId())
            .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado"));
            
        Reserva reserva = new Reserva();
        // Mapear DTO a entidad
        reserva.setEstado("PENDIENTE");
        
        Reserva saved = reservaRepository.save(reserva);
        
        // Notificar al proveedor
        notificacionService.crearNotificacion(
            herramienta.getProveedor().getId(),
            "Nueva solicitud de reserva",
            "Tienes una nueva solicitud para " + herramienta.getNombre()
        );
        
        return mapToDTO(saved);
    }
    
    // Otros métodos de servicio
}