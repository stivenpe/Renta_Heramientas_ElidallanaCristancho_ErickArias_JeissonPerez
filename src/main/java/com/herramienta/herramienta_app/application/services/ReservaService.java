package com.herramienta.herramienta_app.application.services;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.ReservaDto;
import com.herramienta.herramienta_app.domain.entities.Reserva;
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

    public ReservaDto crearReserva(ReservaDto reservaDto) {
        return null;
    }

    public List<ReservaDto> listarPorProveedor(Long proveedorId) {
        List<Reserva> reservas = reservaRepository.findByProveedorId(proveedorId);

        return reservas.stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    private ReservaDto mapToDTO(Reserva reserva) {
        ReservaDto dto = new ReservaDto();
        dto.setId(reserva.getId());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setCostoTotal(reserva.getCostoTotal());
        dto.setEstado(reserva.getEstado());
        dto.setHerramientaId(reserva.getHerramienta().getId());
        dto.setClienteId(reserva.getCliente().getId());
        dto.setProveedorId(reserva.getProveedor().getId());
        return dto;
    }
}
