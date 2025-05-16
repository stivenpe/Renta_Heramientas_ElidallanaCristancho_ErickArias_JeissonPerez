package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.domain.dtos.HerramientaDTO;
import com.herramienta.herramienta_app.domain.entities.*;
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
    
    public List<HerramientaDTO> buscarHerramientasDisponibles() {
        return herramientaRepository.findByCantidadDisponibleGreaterThan(0).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    public HerramientaDTO crearHerramienta(HerramientaDTO herramientaDTO, Long proveedorId) {
        Proveedor proveedor = proveedorRepository.findById(proveedorId)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Proveedor no encontrado"));
            
        Categoria categoria = categoriaRepository.findByNombre(herramientaDTO.getCategoria())
            .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
            
        Herramienta herramienta = new Herramienta();
        herramienta.setNombre(herramientaDTO.getNombre());
        herramienta.setDescripcion(herramientaDTO.getDescripcion());
        herramienta.setModelo(herramientaDTO.getModelo());
        herramienta.setMarca(herramientaDTO.getMarca());
        herramienta.setCostoPorDia(herramientaDTO.getCostoPorDia());
        herramienta.setCantidadDisponible(herramientaDTO.getCantidadDisponible());
        herramienta.setProveedor(proveedor);
        herramienta.setCategoria(categoria);
        
        Herramienta saved = herramientaRepository.save(herramienta);
        return mapToDTO(saved);
    }
    
    private HerramientaDTO mapToDTO(Herramienta herramienta) {
        HerramientaDTO dto = new HerramientaDTO();
        dto.setId(herramienta.getId());
        dto.setNombre(herramienta.getNombre());
        dto.setDescripcion(herramienta.getDescripcion());
        dto.setModelo(herramienta.getModelo());
        dto.setMarca(herramienta.getMarca());
        dto.setCostoPorDia(herramienta.getCostoPorDia());
        dto.setCantidadDisponible(herramienta.getCantidadDisponible());
        dto.setCategoria(herramienta.getCategoria().getNombre());
        dto.setProveedor(herramienta.getProveedor().getNombreEmpresa());
        return dto;
    }
}