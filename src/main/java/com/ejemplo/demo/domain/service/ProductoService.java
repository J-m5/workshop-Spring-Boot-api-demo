package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.ProductoRequestDTO;
import com.ejemplo.demo.api.dto.ProductoResponseDTO;
import com.ejemplo.demo.api.exception.ResourceNotFoundException;
import com.ejemplo.demo.domain.model.Categoria;
import com.ejemplo.demo.domain.model.Producto;
import com.ejemplo.demo.domain.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaService categoriaService;

    public ProductoService(ProductoRepository productoRepository, CategoriaService categoriaService) {
        this.productoRepository = productoRepository;
        this.categoriaService = categoriaService;
    }

    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        Categoria categoria = categoriaService.obtenerEntidadPorId(dto.categoriaId());
        
        Producto producto = new Producto(
            dto.nombre(),
            dto.descripcion(),
            dto.precio(),
            dto.stock(),
            categoria
        );
        
        producto = productoRepository.save(producto);
        return mapToResponse(producto);
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listar() {
        return productoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        return mapToResponse(producto);
    }

    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        
        Categoria categoria = categoriaService.obtenerEntidadPorId(dto.categoriaId());
        
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setCategoria(categoria);
        
        producto = productoRepository.save(producto);
        return mapToResponse(producto);
    }

    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

    private ProductoResponseDTO mapToResponse(Producto producto) {
        return new ProductoResponseDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getStock(),
            producto.getCategoria().getNombre(),
            producto.getCreadoEn(),
            producto.getActualizadoEn()
        );
    }
}
