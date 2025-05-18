package com.herramienta.herramienta_app.infrastructure.repositories.Factura;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.application.services.FacturaService;
import com.herramienta.herramienta_app.domain.entities.Factura;

@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteById(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return facturaRepository.existsById(id);
    }

}