package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.domain.dtos.Factura;
import com.herramienta.herramienta_app.domain.entities.Reserva;
import com.herramienta.herramienta_app.infrastructure.repositories.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public List<Factura> listarTodas() {
        return facturaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Factura obtenerPorId(Long id) {
        com.herramienta.herramienta_app.domain.entities.Factura facturaEntity = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return mapToDTO(facturaEntity);
    }

    public Factura obtenerPorReservaId(Long reservaId) {
        com.herramienta.herramienta_app.domain.entities.Factura facturaEntity = facturaRepository.findByReservaId(reservaId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada para esta reserva"));
        return mapToDTO(facturaEntity);
    }

    public List<Factura> listarPorClienteId(Long clienteId) {
        return facturaRepository.findByReservaClienteId(clienteId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<Factura> listarPorProveedorId(Long proveedorId) {
        return facturaRepository.findByReservaProveedorId(proveedorId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public byte[] descargarPdf(Long id) {
        com.herramienta.herramienta_app.domain.entities.Factura facturaEntity = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return facturaEntity.getPdf().getBytes(StandardCharsets.UTF_8);
    }

    public byte[] descargarXml(Long id) {
        com.herramienta.herramienta_app.domain.entities.Factura facturaEntity = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return facturaEntity.getXml().getBytes(StandardCharsets.UTF_8);
    }

    public void generarFactura(Reserva reserva) {
        com.herramienta.herramienta_app.domain.entities.Factura factura = 
            new com.herramienta.herramienta_app.domain.entities.Factura();
        
        factura.setReserva(reserva);
        factura.setFolio(UUID.randomUUID().toString());
        factura.setFechaEmision(LocalDate.now());

        BigDecimal subtotal = BigDecimal.valueOf(reserva.getCostoTotal()); 
        BigDecimal iva = subtotal.multiply(BigDecimal.valueOf(0.16));
        BigDecimal total = subtotal.add(iva);

        factura.setSubtotal(subtotal.doubleValue());
        factura.setIva(iva.doubleValue());
        factura.setTotal(total.doubleValue());

        factura.setRfcEmisor(reserva.getProveedor().getRfc());
        factura.setRfcReceptor(reserva.getCliente().getRfc());

        factura.setPdf("PDF generado"); // Contenido simulado como String
        factura.setXml("<xml>Factura</xml>"); // Contenido simulado como String

        facturaRepository.save(factura);
    }

    private Factura mapToDTO(com.herramienta.herramienta_app.domain.entities.Factura facturaEntity) {
        Factura dto = new Factura();
        dto.setId(facturaEntity.getId());
        dto.setFolio(facturaEntity.getFolio());
        dto.setFechaEmision(facturaEntity.getFechaEmision());
        dto.setRfcEmisor(facturaEntity.getRfcEmisor());
        dto.setRfcReceptor(facturaEntity.getRfcReceptor());
        dto.setSubtotal(facturaEntity.getSubtotal());
        dto.setIva(facturaEntity.getIva());
        dto.setTotal(facturaEntity.getTotal());
        dto.setReservaId(facturaEntity.getReserva().getId());
        return dto;
    }
}