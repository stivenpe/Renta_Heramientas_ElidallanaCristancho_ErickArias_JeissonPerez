package com.herramienta.herramienta_app.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.LoginResponse;
import com.herramienta.herramienta_app.domain.dtos.RegisterRequest;
import com.herramienta.herramienta_app.domain.entities.Rol;
import com.herramienta.herramienta_app.domain.entities.Usuario;
import com.herramienta.herramienta_app.infrastructure.repositories.Rol.RolRepository;
import com.herramienta.herramienta_app.infrastructure.security.JwtUtils;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;
    private final UsuarioDetailsServiceImpl usuarioDetailsService;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils,
            UsuarioService usuarioService,
            PasswordEncoder passwordEncoder,
            RolRepository rolRepository,
            UsuarioDetailsServiceImpl usuarioDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
        this.usuarioDetailsService = usuarioDetailsService;
    }

    public LoginResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = usuarioDetailsService.loadUserByUsername(email);
        String token = jwtUtils.createToken(userDetails);

        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                .orElseThrow(() -> new RuntimeException("Usuario sin rol"));

        return new LoginResponse(token, role);
    }

    public LoginResponse register(RegisterRequest request) {
        if (usuarioService.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email ya registrado");
        }

        String rolNombre = (request.rol() != null && !request.rol().isBlank())
                ? request.rol().toUpperCase()
                : "CLIENTE";

        Rol rolAsignado = rolRepository.findByNombre(rolNombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rolNombre));

        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre());
        usuario.setEmail(request.email());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setTelefono(request.telefono());
        usuario.setDireccion(request.direccion());
        usuario.setActivo(true);
        usuario.setRol(rolAsignado);

        Usuario savedUsuario = usuarioService.save(usuario);

        UserDetails userDetails = usuarioDetailsService.loadUserByUsername(savedUsuario.getEmail());
        String jwtToken = jwtUtils.createToken(userDetails);

        return new LoginResponse(jwtToken, rolAsignado.getNombre());
    }

}