package com.herramienta.herramienta_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.herramienta.herramienta_app.domain.entities.Rol;
import com.herramienta.herramienta_app.infrastructure.repositories.Rol.RolRepository;

@SpringBootApplication
public class HerramientaAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(HerramientaAppApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RolRepository rolRepository) {
        return args -> {
            if (rolRepository.count() == 0) {
                Rol admin = new Rol();
                admin.setNombre("ADMIN");
                rolRepository.save(admin);

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
