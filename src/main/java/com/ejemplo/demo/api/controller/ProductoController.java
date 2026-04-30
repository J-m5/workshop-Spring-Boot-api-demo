package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.ProductoRequestDTO;
import com.ejemplo.demo.api.dto.ProductoResponseDTO;
import com.ejemplo.demo.domain.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listar() {
        List<ProductoResponseDTO> productos = productoService.listar();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        ProductoResponseDTO producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoRequestDTO dto) {
        ProductoResponseDTO nuevoProducto = productoService.crear(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {
        ProductoResponseDTO productoActualizado = productoService.actualizar(id, dto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}