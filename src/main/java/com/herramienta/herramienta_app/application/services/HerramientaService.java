package com.herramienta.herramienta_app.application.services;

import java.util.List;

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

    public List<Herramienta> obtenerTodas() {
        return herramientaRepository.findAll();
    }

    public Herramienta obtenerPorId(Long id) {
        return herramientaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Herramienta no encontrada"));
    }

    public Herramienta actualizarHerramienta(Long id, HerramientaDto dto) {
        Herramienta herramienta = obtenerPorId(id);
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        herramienta.setNombre(dto.getNombre());
        herramienta.setDescripcion(dto.getDescripcion());
        herramienta.setCostoDia(dto.getCostoDia());
        herramienta.setDeposito(dto.getDeposito());
        herramienta.setCategoria(categoria);
        herramienta.setProveedor(proveedor);

        return herramientaRepository.save(herramienta);
    }

    public void eliminarHerramienta(Long id) {
        herramientaRepository.deleteById(id);
    }
}
