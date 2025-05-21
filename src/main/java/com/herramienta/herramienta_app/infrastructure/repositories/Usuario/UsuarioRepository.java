package com.herramienta.herramienta_app.infrastructure.repositories.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herramienta.herramienta_app.domain.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}