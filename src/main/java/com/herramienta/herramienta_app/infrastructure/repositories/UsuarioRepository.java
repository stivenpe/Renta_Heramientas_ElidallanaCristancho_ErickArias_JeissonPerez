package com.herramienta.herramienta_app.infrastructure.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herramienta.herramienta_app.domain.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    javaCopiarEditar@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
}
