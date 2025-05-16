package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.domain.dtos.FacturaDto;
import com.herramienta.herramienta_app.domain.entities.Factura;
import com.herramienta.herramienta_app.infrastructure.repositories.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public List<FacturaDto> listarTodas() {
        return facturaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public FacturaDto obtenerPorId(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return mapToDTO(factura);
    }

    public FacturaDto obtenerPorReservaId(Long reservaId) {
        Factura factura = facturaRepository.findByReservaId(reservaId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada para esta reserva"));
        return mapToDTO(factura);
    }

    public List<FacturaDto> listarPorClienteId(Long clienteId) {
        return facturaRepository.findByReservaClienteId(clienteId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<FacturaDto> listarPorProveedorId(Long proveedorId) {
        return facturaRepository.findByReservaProveedorId(proveedorId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public byte[] descargarPdf(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return factura.getPdf().getBytes();
    }

    public byte[] descargarXml(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return factura.getXml().getBytes();
    }

    private FacturaDto mapToDTO(Factura factura) {
        FacturaDto dto = new FacturaDto();
        dto.setId(factura.getId());
        dto.setFolio(factura.getFolio());
        dto.setFechaEmision(factura.getFechaEmision());
        dto.setRfcEmisor(factura.getRfcEmisor());
        dto.setRfcReceptor(factura.getRfcReceptor());
        dto.setSubtotal(factura.getSubtotal());
        dto.setIva(factura.getIva());
        dto.setTotal(factura.getTotal());
        dto.setReservaId(factura.getReserva().getId());
        return dto;
    }
}