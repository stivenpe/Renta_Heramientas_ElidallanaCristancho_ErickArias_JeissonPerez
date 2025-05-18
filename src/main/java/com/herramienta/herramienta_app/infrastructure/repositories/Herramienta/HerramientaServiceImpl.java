package com.herramienta.herramienta_app.infrastructure.repositories.Herramienta;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.application.services.HerramientaService;
import com.herramienta.herramienta_app.domain.entities.Herramienta;

import java.util.List;
import java.util.Optional;

@Service
public class HerramientaServiceImpl implements HerramientaService {

    private final HerramientaRepository herramientaRepository;

    public HerramientaServiceImpl(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }
    @Override
    public List<Herramienta> findAll() {
        return herramientaRepository.findAll();
    }

    @Override
    public Optional<Herramienta> findById(Long id) {
        return herramientaRepository.findById(id);
    }

    @Override
    public Herramienta save(Herramienta herramienta) {
        return herramientaRepository.save(herramienta);
    }

    @Override
    public void deleteById(Long id) {
        herramientaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return herramientaRepository.existsById(id);
    }
}