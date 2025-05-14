package com.herramienta.herramienta_app.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaResponseDto {
    private Long idReserva;
    private String nombreCliente;
    private String nombreHerramienta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal total;
    private String estado;

    // Getters y Setters
}
