package com.herramienta.herramienta_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.herramienta.herramienta_app.domain.entities.Permiso;
import com.herramienta.herramienta_app.domain.entities.Rol;
import com.herramienta.herramienta_app.infrastructure.repositories.PermisoRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.RolRepository;

@SpringBootApplication
public class HerramientaAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(HerramientaAppApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(RolRepository rolRepository, PermisoRepository permisoRepository) {
        return args -> {
            if (rolRepository.count() == 0) {
                // Inicialización de permisos
                Permiso gestionUsuarios = new Permiso();
                gestionUsuarios.setNombre("GESTION_USUARIOS");
                gestionUsuarios.setDescripcion("Permite gestionar usuarios");
                permisoRepository.save(gestionUsuarios);
                
                // Inicialización de roles
                Rol admin = new Rol();
                admin.setNombre("ADMIN");
                admin.getPermisos().add(gestionUsuarios);
                rolRepository.save(admin);
                
                // añadimo más roles y permisos aquí
                Rol proveedor = new Rol();
                proveedor.setNombre("PROVEEDOR");

                rolRepository.save(proveedor);
                
                Rol cliente = new Rol();
                cliente.setNombre("CLIENTE");
				
                rolRepository.save(cliente);
            }
        };
    }
}
