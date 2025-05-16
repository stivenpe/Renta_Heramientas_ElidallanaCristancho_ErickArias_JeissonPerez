package com.herramienta.herramienta_app.application.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.dtos.HerramientaDto;
import com.herramienta.herramienta_app.domain.entities.Categoria;
import com.herramienta.herramienta_app.domain.entities.Herramienta;
import com.herramienta.herramienta_app.domain.entities.Proveedor;
import com.herramienta.herramienta_app.infrastructure.repositories.HerramientaRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HerramientaService {
    private final HerramientaRepository herramientaRepository;
    private final ProveedorRepository proveedorRepository;
    private final CategoriaRepository categoriaRepository;
    
    public List<HerramientaDto> buscarHerramientasDisponibles() {
        return herramientaRepository.findDisponibles().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    public HerramientaDto crearHerramienta(HerramientaDto herramientaDTO, Long proveedorId) {
        Proveedor proveedor = proveedorRepository.findById(proveedorId)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Proveedor no encontrado"));
            
        Categoria categoria = categoriaRepository.findByNombre(herramientaDTO.getCategoria())
            .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
            
        Herramienta herramienta = new Herramienta();
        // Mapear DTO a entidad
        herramienta.setProveedor(proveedor);
        herramienta.setCategoria(categoria);
        
        Herramienta saved = herramientaRepository.save(herramienta);
        return mapToDTO(saved);
    }
    
    // Otros métodos de servicio
}