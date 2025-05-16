package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.dtos.FacturaDTO;
import com.herramienta.herramienta_app.entities.Factura;
import com.herramienta.herramienta_app.infrastructure.repositories.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public List<FacturaDTO> listarTodas() {
        return facturaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public FacturaDTO obtenerPorId(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return mapToDTO(factura);
    }

    public FacturaDTO obtenerPorReservaId(Long reservaId) {
        Factura factura = facturaRepository.findByReservaId(reservaId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada para esta reserva"));
        return mapToDTO(factura);
    }

    public List<FacturaDTO> listarPorClienteId(Long clienteId) {
        return facturaRepository.findByReservaClienteId(clienteId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<FacturaDTO> listarPorProveedorId(Long proveedorId) {
        return facturaRepository.findByReservaProveedorId(proveedorId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public byte[] descargarPdf(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        // Implementar lógica para obtener el PDF
        return factura.getPdf().getBytes();
    }

    public byte[] descargarXml(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        // Implementar lógica para obtener el XML
        return factura.getXml().getBytes();
    }

    private FacturaDTO mapToDTO(Factura factura) {
        FacturaDTO dto = new FacturaDTO();
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