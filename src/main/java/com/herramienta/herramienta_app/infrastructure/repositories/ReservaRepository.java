package com.herramienta.herramienta_app.infrastructure.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.herramienta.herramienta_app.domain.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByClienteId(Long clienteId);
    List<Reserva> findByProveedorId(Long proveedorId);
    List<Reserva> findByHerramientaId(Long herramientaId);
    
    @Query("SELECT r FROM Reserva r WHERE r.fechaInicio BETWEEN :inicio AND :fin OR r.fechaFin BETWEEN :inicio AND :fin")
    List<Reserva> findReservasEntreFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
}
