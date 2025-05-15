package com.herramienta.herramienta_app.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.herramienta.herramienta_app.domain.entities.Usuario;
import com.herramienta.herramienta_app.infrastructure.repositories.UsuarioRepository;
import com.herramienta.herramienta_app.security.JwtUtils;

public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private JwtUtils jwtUtils;

    public String login(String email, String password) {
        Usuario usuario = usuarioRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("No encontrado"));

        if (!password.equals(usuario.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        return jwtUtils.generateToken(usuario);
    }
}

