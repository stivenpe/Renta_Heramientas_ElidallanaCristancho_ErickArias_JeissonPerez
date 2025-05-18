package com.herramienta.herramienta_app.application.services;

import java.util.List;
import java.util.Optional;
import com.herramienta.herramienta_app.domain.entities.Usuario;

public interface UsuarioService {
    Optional<Usuario> findOneByUsuarioname(String username);
    boolean existsByEmail(String email);
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    void deleteById(Long id);
    Usuario update(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
    boolean existsById(Long id);
}