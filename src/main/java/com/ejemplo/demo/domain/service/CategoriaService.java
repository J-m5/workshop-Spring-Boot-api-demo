package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.CategoriaRequestDTO;
import com.ejemplo.demo.api.dto.CategoriaResponseDTO;
import com.ejemplo.demo.api.exception.ResourceNotFoundException;
import com.ejemplo.demo.domain.model.Categoria;
import com.ejemplo.demo.domain.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponseDTO crear(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria(dto.nombre(), dto.descripcion());
        categoria = categoriaRepository.save(categoria);
        return mapToResponse(categoria);
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listar() {
        return categoriaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDTO obtenerPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
        return mapToResponse(categoria);
    }

    @Transactional(readOnly = true)
    public Categoria obtenerEntidadPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
    }

    public CategoriaResponseDTO actualizar(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
        
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());
        
        categoria = categoriaRepository.save(categoria);
        return mapToResponse(categoria);
    }

    public void eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaResponseDTO mapToResponse(Categoria categoria) {
        return new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNombre(),
            categoria.getDescripcion(),
            categoria.getCreadoEn(),
            categoria.getActualizadoEn()
        );
    }
}
