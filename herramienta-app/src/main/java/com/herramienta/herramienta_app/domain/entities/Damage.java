package com.herramienta.herramienta_app.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Damage {
     private Long idDaño;
    private Reserva reserva;
    private String descripcion;
    private BigDecimal costoReparacion;
    private LocalDateTime fechaReporte;
    public Long getIdDaño() {
        return idDaño;
    }
    public void setIdDaño(Long idDaño) {
        this.idDaño = idDaño;
    }
    public Reserva getReserva() {
        return reserva;
    }
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public BigDecimal getCostoReparacion() {
        return costoReparacion;
    }
    public void setCostoReparacion(BigDecimal costoReparacion) {
        this.costoReparacion = costoReparacion;
    }
    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }
    public void setFechaReporte(LocalDateTime fechaReporte) {
        this.fechaReporte = fechaReporte;
    }



}
