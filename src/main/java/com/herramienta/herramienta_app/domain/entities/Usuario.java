package com.herramienta.herramienta_app.domain.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private boolean activo = true;
    
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
    
    @OneToMany(mappedBy = "usuario")
    private List<Notificacion> notificaciones;
    
    // Getters y setters
}

