package com.herramienta.herramienta_app.domain.entities;

import java.time.LocalDateTime;

public class Notificacion {
    private Long idNotificacion;
    private Usuario usuario;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean leida;
    private String tipo;
    public Long getIdNotificacion() {
        return idNotificacion;
    }
    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public Boolean getLeida() {
        return leida;
    }
    public void setLeida(Boolean leida) {
        this.leida = leida;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



}
