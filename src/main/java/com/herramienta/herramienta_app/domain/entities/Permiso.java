package com.herramienta.herramienta_app.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permisos")
public class Permiso {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermiso;

    public Long getIdPermiso() {
        return idPermiso;
    }
     public void setIdPermiso(Long idPermiso) {
         this.idPermiso = idPermiso;
     }
     public String getNombre() {
         return nombre;
     }
     public void setNombre(String nombre) {
         this.nombre = nombre;
     }
     public String getDescripcion() {
         return descripcion;
     }
     public void setDescripcion(String descripcion) {
         this.descripcion = descripcion;
     }
    private String nombre;
    private String descripcion;

}
