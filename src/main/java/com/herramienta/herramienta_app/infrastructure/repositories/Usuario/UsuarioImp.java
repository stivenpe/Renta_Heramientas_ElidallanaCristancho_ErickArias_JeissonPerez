package com.herramienta.herramienta_app.infrastructure.repositories.Usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.herramienta.herramienta_app.application.services.UsuarioService;
import com.herramienta.herramienta_app.domain.entities.Usuario;
import jakarta.transaction.Transactional;

@Service
public class UsuarioImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> findOneByUsuarioname(String username) {
        return usuarioRepository.findByNombre(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }
}