package com.herramienta.herramienta_app.infrastructure.repositories.Proveedor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.application.services.ProveedorService;
import com.herramienta.herramienta_app.domain.entities.Proveedor;
import com.herramienta.herramienta_app.infrastructure.repositories.Proveedor.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> findById(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteById(Long id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return proveedorRepository.existsById(id);
    }
}