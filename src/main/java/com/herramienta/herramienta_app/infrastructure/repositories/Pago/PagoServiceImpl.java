package com.herramienta.herramienta_app.infrastructure.repositories.Pago;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.application.services.PagoService;
import com.herramienta.herramienta_app.domain.entities.Pago;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> findById(Long id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }

    public Pago procesarPago(Pago pagoDTO) {
        if (pagoDTO.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        return pagoRepository.save(pagoDTO);
    }
}