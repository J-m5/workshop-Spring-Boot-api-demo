package com.ejemplo.demo.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductoResponseDTO(
    Long id,
    String nombre,
    String descripcion,
    BigDecimal precio,
    Integer stock,
    String categoriaNombre,
    Instant creadoEn,
    Instant actualizadoEn
) {}
