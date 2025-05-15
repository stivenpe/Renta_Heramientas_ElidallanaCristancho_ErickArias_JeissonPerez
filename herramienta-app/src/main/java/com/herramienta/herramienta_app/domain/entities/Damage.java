package com.herramienta.herramienta_app.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "damages")
public class Damage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDanio; // ✅ Evita "ñ" en nombres de variables

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costoReparacion;

    @Column(nullable = false)
    private LocalDateTime fechaReporte;

    // Getters y Setters

    public Long getIdDanio() {
        return idDanio;
    }

    public void setIdDanio(Long idDanio) {
        this.idDanio = idDanio;
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
