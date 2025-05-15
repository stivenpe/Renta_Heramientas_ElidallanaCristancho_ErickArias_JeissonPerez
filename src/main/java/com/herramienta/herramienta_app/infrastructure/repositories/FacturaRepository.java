package com.herramienta.herramienta_app.infrastructure.repositories;

import com.herramienta.herramienta_app.domain.entities.Factura;

public interface FacturaRepository {


     void save(Factura factura);
    Factura findById(Long idFactura);
    void update(Factura factura);
    void delete(Long idFactura);

}
