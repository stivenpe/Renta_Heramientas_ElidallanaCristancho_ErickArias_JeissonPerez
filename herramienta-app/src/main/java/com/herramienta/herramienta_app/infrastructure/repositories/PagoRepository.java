package com.herramienta.herramienta_app.infrastructure.repositories;

import java.util.List;

import com.herramienta.herramienta_app.domain.entities.Pago;

public interface PagoRepository {


    void save(Pago pago);
    Pago findById(Long idPago);
    List<Pago> findAll();
    void update(Pago pago);
    void delete(Long idPago);

}
