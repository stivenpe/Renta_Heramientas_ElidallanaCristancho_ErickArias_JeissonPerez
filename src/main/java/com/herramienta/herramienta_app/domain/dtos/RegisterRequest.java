package com.herramienta.herramienta_app.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "Nombre es requerido")
    String nombre,
    
    @NotBlank(message = "Email es requerido")
    @Email(message = "Email should be valid")
    String email,
    
    @NotBlank(message = "Contraseña es requerido")
    @Size(min = 8, message = "Contraseña debe tener al menos 8 caracteres")
    String password,
    
    @NotBlank(message = "Direccion es requerido")
    String direccion,
    
    @NotBlank(message = "Telefono no puede quedar en blanco")
    String telefono,

    String rol
) {}