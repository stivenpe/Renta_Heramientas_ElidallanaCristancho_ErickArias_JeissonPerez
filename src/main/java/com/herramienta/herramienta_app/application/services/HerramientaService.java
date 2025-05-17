package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.domain.exceptions.UsuarioNoEncontradoException;
import com.herramienta.herramienta_app.infrastructure.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HerramientaService {
    private final HerramientaRepository herramientaRepository;
    private final ProveedorRepository proveedorRepository;
    private final CategoriaRepository categoriaRepository;
    
    public List<com.herramienta.herramienta_app.domain.dtos.Herramienta> buscarHerramientasDisponibles() {
        return herramientaRepository.findByCantidadDisponibleGreaterThan(0).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    public com.herramienta.herramienta_app.domain.dtos.Herramienta crearHerramienta(
            com.herramienta.herramienta_app.domain.dtos.Herramienta herramientaDTO, 
            Long proveedorId) {
        
        com.herramienta.herramienta_app.domain.entities.Proveedor proveedor = proveedorRepository.findById(proveedorId)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Proveedor no encontrado"));
            
        com.herramienta.herramienta_app.domain.entities.Categoria categoria = categoriaRepository.findByNombre(herramientaDTO.getCategoria())
            .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
            
        com.herramienta.herramienta_app.domain.entities.Herramienta herramientaEntity = 
            new com.herramienta.herramienta_app.domain.entities.Herramienta();
        
        herramientaEntity.setNombre(herramientaDTO.getNombre());
        herramientaEntity.setDescripcion(herramientaDTO.getDescripcion());
        herramientaEntity.setModelo(herramientaDTO.getModelo());
        herramientaEntity.setMarca(herramientaDTO.getMarca());
        herramientaEntity.setCostoPorDia(herramientaDTO.getCostoPorDia());
        herramientaEntity.setCantidadDisponible(herramientaDTO.getCantidadDisponible());
        herramientaEntity.setProveedor(proveedor);
        herramientaEntity.setCategoria(categoria);
        herramientaEntity.setActiva(true);
        
        com.herramienta.herramienta_app.domain.entities.Herramienta saved = 
            herramientaRepository.save(herramientaEntity);
        
        return mapToDTO(saved);
    }
    
    private com.herramienta.herramienta_app.domain.dtos.Herramienta mapToDTO(
            com.herramienta.herramienta_app.domain.entities.Herramienta herramientaEntity) {
        
        com.herramienta.herramienta_app.domain.dtos.Herramienta dto = 
            new com.herramienta.herramienta_app.domain.dtos.Herramienta();
        
        dto.setId(herramientaEntity.getId());
        dto.setNombre(herramientaEntity.getNombre());
        dto.setDescripcion(herramientaEntity.getDescripcion());
        dto.setModelo(herramientaEntity.getModelo());
        dto.setMarca(herramientaEntity.getMarca());
        dto.setCostoPorDia(herramientaEntity.getCostoPorDia());
        dto.setCantidadDisponible(herramientaEntity.getCantidadDisponible());
        dto.setCategoria(herramientaEntity.getCategoria().getNombre());
        dto.setProveedor(herramientaEntity.getProveedor().getNombreEmpresa());
        
        return dto;
    }
}