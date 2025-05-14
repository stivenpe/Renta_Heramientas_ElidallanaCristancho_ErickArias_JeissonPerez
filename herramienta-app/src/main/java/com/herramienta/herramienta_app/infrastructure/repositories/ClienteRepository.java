package com.herramienta.herramienta_app.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herramienta.herramienta_app.domain.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

