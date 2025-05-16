package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.domain.dtos.FacturaDto;
import com.herramienta.herramienta_app.domain.entities.Factura;
import com.herramienta.herramienta_app.domain.entities.Reserva;
import com.herramienta.herramienta_app.infrastructure.repositories.FacturaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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

    public void generarFactura(Reserva reserva) {
        Factura factura = new Factura();
        factura.setReserva(reserva);
        factura.setFolio(UUID.randomUUID().toString());
        factura.setFechaEmision(LocalDate.now());

        // Asegúrate de que Reserva tenga un método getPrecio() que retorne BigDecimal
    BigDecimal subtotal = BigDecimal.valueOf(reserva.getCostoTotal()); 
        BigDecimal iva = subtotal.multiply(BigDecimal.valueOf(0.16));
        BigDecimal total = subtotal.add(iva);

        factura.setSubtotal(subtotal.doubleValue());
        factura.setIva(iva.doubleValue());
        factura.setTotal(total.doubleValue()); // ✅ aquí estaba el detalle

        factura.setRfcEmisor(reserva.getProveedor().getRfc());
        factura.setRfcReceptor(reserva.getCliente().getRfc());

        factura.setPdf("PDF generado"); // simulación
        factura.setXml("<xml>Factura</xml>"); // simulación

        facturaRepository.save(factura);
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
