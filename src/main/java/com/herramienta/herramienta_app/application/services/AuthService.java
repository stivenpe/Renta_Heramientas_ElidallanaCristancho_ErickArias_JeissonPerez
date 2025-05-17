package com.herramienta.herramienta_app.application.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.LoginRequest;
import com.herramienta.herramienta_app.domain.dtos.LoginResponse;
import com.herramienta.herramienta_app.domain.dtos.Usuario;
import com.herramienta.herramienta_app.domain.exceptions.UsuarioNoEncontradoException;
import com.herramienta.herramienta_app.infrastructure.repositories.RolRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.UsuarioRepository;
import com.herramienta.herramienta_app.infrastructure.security.JwtUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    
    public LoginResponse login(LoginRequest request) {
        com.herramienta.herramienta_app.domain.entities.Usuario usuarioEntity = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
            
        if (!passwordEncoder.matches(request.getPassword(), usuarioEntity.getPassword())) {
            throw new UsuarioNoEncontradoException("Credenciales inválidas");
        }
        
        if (!usuarioEntity.isActivo()) {
            throw new UsuarioNoEncontradoException("Usuario desactivado");
        }
        
        String token = jwtUtils.generateToken(usuarioEntity);
        Usuario usuarioDTO = mapToDTO(usuarioEntity);
        
        return new LoginResponse(token, usuarioDTO);
    }
    
    private Usuario mapToDTO(com.herramienta.herramienta_app.domain.entities.Usuario usuario) {
        Usuario dto = new Usuario();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setDireccion(usuario.getDireccion());
        dto.setRol(usuario.getRol().getNombre());
        return dto;
    }
}