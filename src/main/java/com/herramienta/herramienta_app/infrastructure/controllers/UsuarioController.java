package com.herramienta.herramienta_app.infrastructure.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.herramienta.herramienta_app.application.services.UsuarioService;
import com.herramienta.herramienta_app.domain.dtos.UsuarioDto;
import com.herramienta.herramienta_app.domain.entities.Usuario;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDto>> listarTodos() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDto> dtos = usuarios.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDto> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id).orElseThrow();
        return ResponseEntity.ok(toDto(usuario));
    }

    @GetMapping("/buscar")
    public ResponseEntity<UsuarioDto> obtenerPorEmail(@RequestParam String email) {
        Usuario usuario = usuarioService.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(toDto(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody Usuario usuario) {
        Usuario saved = usuarioService.save(usuario);
        return ResponseEntity.status(201).body(toDto(saved));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDto> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario updated = usuarioService.update(usuario);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Método auxiliar para convertir Usuario a UsuarioDto
    private UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getTelefono(),
            usuario.getDireccion(),
            usuario.getRol().getNombre()
        );
    }
}