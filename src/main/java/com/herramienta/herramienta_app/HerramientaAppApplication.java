package com.herramienta.herramienta_app;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.herramienta.herramienta_app.application.services.UsuarioService;
import com.herramienta.herramienta_app.domain.entities.Rol;
import com.herramienta.herramienta_app.domain.entities.Usuario;
import com.herramienta.herramienta_app.infrastructure.repositories.Rol.RolRepository;

@SpringBootApplication
public class HerramientaAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(HerramientaAppApplication.class, args);
    }

    @Bean
    CommandLineRunner initAdminusuario(UsuarioService usuarioService, RolRepository rolRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioService.findOneByUsuarioname("admin").isEmpty()) {
                Optional<Rol> adminRolOpt = rolRepository.findByNombre("ADMIN");
                if (adminRolOpt.isPresent()) {
                    Rol adminRol = adminRolOpt.get();

                    Usuario adminusuario = new Usuario();
                    adminusuario.setNombre("admin");
                    adminusuario.setEmail("eli@gmail.com");
                    adminusuario.setPassword(passwordEncoder.encode("123456789"));
                    adminusuario.setDireccion("Dirección Administrativa");
                    adminusuario.setTelefono("0000000000");
                    adminusuario.setActivo(true);
                    adminusuario.setRol(adminRol);

                    usuarioService.save(adminusuario);

                    System.out.println("Usuario predeterminado creado con éxito.");
                }
            } else {
                System.out.println("El usuario ya existe.");
            }
        };
    }
}
