package com.herramienta.herramienta_app.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.herramienta.herramienta_app.domain.entities.Herramienta;

public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
    List<Herramienta> findByProveedorId(Long proveedorId);
    List<Herramienta> findByCategoriaNombre(String categoria);
    List<Herramienta> findByNombreContainingIgnoreCase(String nombre);
    List<Herramienta> findByCantidadDisponibleGreaterThan(int cantidad);

    
    @Query("SELECT h FROM Herramienta h WHERE h.cantidadDisponible > 0 AND h.activa = true")
    List<Herramienta> findDisponibles();
}
