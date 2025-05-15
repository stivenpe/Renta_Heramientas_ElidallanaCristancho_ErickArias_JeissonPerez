package com.herramienta.herramienta_app.security;

import java.util.stream.Collectors;

import com.herramienta.herramienta_app.domain.entities.Rol;
import com.herramienta.herramienta_app.domain.entities.Usuario;

import io.jsonwebtoken.Jwts;

public class JwtUtils { private final String SECRET_KEY = "secreto123";

    public String generateToken(Usuario usuario) {
        return Jwts.builder()
            .setSubject(usuario.getEmail())
            .claim("roles", usuario.getRoles().stream().map(Rol::getNombre).collect(Collectors.toList()))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


