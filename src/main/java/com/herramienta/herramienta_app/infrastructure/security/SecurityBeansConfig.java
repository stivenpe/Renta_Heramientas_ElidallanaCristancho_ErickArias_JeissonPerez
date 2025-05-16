package com.herramienta.herramienta_app.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class SecurityBeansConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // Implementa la lógica real para cargar el usuario (desde BD)
                throw new UsernameNotFoundException("User not found");
            }
        };
    }
}

