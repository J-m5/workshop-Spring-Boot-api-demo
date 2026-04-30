package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.CategoriaRequestDTO;
import com.ejemplo.demo.api.dto.CategoriaResponseDTO;
import com.ejemplo.demo.domain.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        List<CategoriaResponseDTO> categorias = categoriaService.listar();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerPorId(@PathVariable Long id) {
        CategoriaResponseDTO categoria = categoriaService.obtenerPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crear(@Valid @RequestBody CategoriaRequestDTO dto) {
        CategoriaResponseDTO nuevaCategoria = categoriaService.crear(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO dto) {
        CategoriaResponseDTO categoriaActualizada = categoriaService.actualizar(id, dto);
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}