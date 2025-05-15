package com.herramienta.herramienta_app.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.herramienta.herramienta_app.domain.entities.Usuario;
import com.herramienta.herramienta_app.infrastructure.repositories.UsuarioRepository;

public class AuthService {
    @Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    
    public LoginResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
            
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new UsuarioNoEncontradoException("Credenciales inválidas");
        }
        
        if (!usuario.isActivo()) {
            throw new UsuarioNoEncontradoException("Usuario desactivado");
        }
        
        String token = jwtUtils.generateToken(usuario);
        UsuarioDTO usuarioDTO = mapToDTO(usuario);
        
        return new LoginResponseDTO(token, usuarioDTO);
    }
    
    private UsuarioDTO mapToDTO(Usuario usuario) {
        // Mapear usuario a DTO
    }
}
}
