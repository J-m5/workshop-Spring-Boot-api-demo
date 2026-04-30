package com.ejemplo.demo.api.dto;

import java.time.Instant;

public record CategoriaResponseDTO(
    Long id,
    String nombre,
    String descripcion,
    Instant creadoEn,
    Instant actualizadoEn
) {}
