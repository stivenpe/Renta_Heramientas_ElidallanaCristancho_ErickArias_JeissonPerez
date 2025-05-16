package com.herramienta.herramienta_app.application.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.LoginRequestDto;
import com.herramienta.herramienta_app.domain.dtos.LoginResponseDto;
import com.herramienta.herramienta_app.domain.dtos.UsuarioDto;
import com.herramienta.herramienta_app.domain.entities.Usuario;
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
    
    public LoginResponseDto login(LoginRequestDto request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
            
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new UsuarioNoEncontradoException("Credenciales inválidas");
        }
        
        if (!usuario.isActivo()) {
            throw new UsuarioNoEncontradoException("Usuario desactivado");
        }
        
        String token = jwtUtils.generateToken(usuario);
        UsuarioDto usuarioDTO = mapToDTO(usuario);
        
        return new LoginResponseDto(token, usuarioDTO);
    }
    
    private UsuarioDto mapToDTO(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setDireccion(usuario.getDireccion());
        dto.setRol(usuario.getRol().getNombre());
        return dto;
    }
}