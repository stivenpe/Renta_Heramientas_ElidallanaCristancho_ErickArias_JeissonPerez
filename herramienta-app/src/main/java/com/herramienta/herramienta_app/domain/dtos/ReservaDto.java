package com.herramienta.herramienta_app.domain.dtos;

import java.time.LocalDate;
import java.math.BigDecimal;

public class ReservaDto {

    private Long idCliente;
    private Long idHerramienta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal total;


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdHerramienta() {
        return idHerramienta;
    }

    public void setIdHerramienta(Long idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
