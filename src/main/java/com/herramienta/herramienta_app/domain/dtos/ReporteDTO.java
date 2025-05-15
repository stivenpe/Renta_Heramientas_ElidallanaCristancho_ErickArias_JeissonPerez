package com.herramienta.herramienta_app.domain.dtos;

import java.time.LocalDate;

public class ReporteDTO {
    private String tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Object datos;
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    public Object getDatos() {
        return datos;
    }
    public void setDatos(Object datos) {
        this.datos = datos;
    }


}
