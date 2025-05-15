package com.herramienta.herramienta_app.domain.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "herramientas")
public class Herramienta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @Column(name = "costo_dia")
    private BigDecimal costoDia;

    private BigDecimal deposito;
    private Boolean disponible;

    // Agregamos la relación con Proveedor
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    // Agregamos la relación con Categoria
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    // ======== Getters y Setters ========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getCostoDia() {
        return costoDia;
    }

    public void setCostoDia(BigDecimal costoDia) {
        this.costoDia = costoDia;
    }

    public BigDecimal getDeposito() {
        return deposito;
    }

    public void setDeposito(BigDecimal deposito) {
        this.deposito = deposito;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
