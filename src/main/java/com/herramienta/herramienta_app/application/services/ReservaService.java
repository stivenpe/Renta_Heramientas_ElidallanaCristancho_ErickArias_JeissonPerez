package com.herramienta.herramienta_app.application.services;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.Reserva;
import com.herramienta.herramienta_app.infrastructure.repositories.HerramientaRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.ReservaRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final HerramientaRepository herramientaRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificacionService notificacionService;

    public com.herramienta.herramienta_app.domain.dtos.Reserva crearReserva(com.herramienta.herramienta_app.domain.dtos.Reserva reservaDto) {
        return null;
    }

    public List<com.herramienta.herramienta_app.domain.dtos.Reserva> listarPorProveedor(Long proveedorId) {
        List<com.herramienta.herramienta_app.domain.entities.Reserva> reservasEntities = 
            reservaRepository.findByProveedorId(proveedorId);

        return reservasEntities.stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    private com.herramienta.herramienta_app.domain.dtos.Reserva mapToDTO(com.herramienta.herramienta_app.domain.entities.Reserva reservaEntity) {
        com.herramienta.herramienta_app.domain.dtos.Reserva dto = new com.herramienta.herramienta_app.domain.dtos.Reserva();
        dto.setId(reservaEntity.getId());
        dto.setFechaInicio(reservaEntity.getFechaInicio());
        dto.setFechaFin(reservaEntity.getFechaFin());
        dto.setCostoTotal(reservaEntity.getCostoTotal());
        dto.setEstado(reservaEntity.getEstado());
        dto.setHerramientaId(reservaEntity.getHerramienta().getId());
        dto.setClienteId(reservaEntity.getCliente().getId());
        dto.setProveedorId(reservaEntity.getProveedor().getId());
        return dto;
    }
}