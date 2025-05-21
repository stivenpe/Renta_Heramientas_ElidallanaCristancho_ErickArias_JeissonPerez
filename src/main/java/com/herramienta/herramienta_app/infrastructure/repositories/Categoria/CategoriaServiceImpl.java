package com.herramienta.herramienta_app.infrastructure.repositories.Categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.application.services.CategoriaService;
import com.herramienta.herramienta_app.domain.entities.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new IllegalArgumentException("La categoría ya existe");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return categoriaRepository.existsById(id);
    }
}