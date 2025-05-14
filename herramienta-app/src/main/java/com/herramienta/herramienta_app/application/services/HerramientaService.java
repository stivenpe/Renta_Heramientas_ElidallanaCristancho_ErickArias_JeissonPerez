package com.herramienta.herramienta_app.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.HerramientaDto;
import com.herramienta.herramienta_app.domain.entities.Categoria;
import com.herramienta.herramienta_app.domain.entities.Herramienta;
import com.herramienta.herramienta_app.domain.entities.Proveedor;
import com.herramienta.herramienta_app.infrastructure.repositories.CategoriaRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.HerramientaRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.ProveedorRepository;

@Service
public class HerramientaService {

    @Autowired
    private HerramientaRepository herramientaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    public Herramienta crearHerramienta(HerramientaDto dto) {
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        Herramienta herramienta = new Herramienta();
        herramienta.setNombre(dto.getNombre());
        herramienta.setDescripcion(dto.getDescripcion());
        herramienta.setCostoDia(dto.getCostoDia());
        herramienta.setDeposito(dto.getDeposito());
        herramienta.setCategoria(categoria);
        herramienta.setProveedor(proveedor);
        herramienta.setDisponible(true);

        return herramientaRepository.save(herramienta);
    }

    // Otros métodos CRUD si los necesitas...
}
