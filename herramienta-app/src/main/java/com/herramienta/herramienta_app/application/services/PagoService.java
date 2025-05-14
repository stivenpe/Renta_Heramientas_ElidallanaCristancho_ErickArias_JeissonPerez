package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.domain.entities.Pago;
import com.herramienta.herramienta_app.infrastructure.repositories.PagoRepository;

public class PagoService {
     private PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public void procesarPago(Pago pago) {
        
        pagoRepository.save(pago);
    }

    public Pago obtenerPago(Long idPago) {
        return pagoRepository.findById(idPago);
    }

}
