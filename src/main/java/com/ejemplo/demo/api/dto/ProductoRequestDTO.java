package com.ejemplo.demo.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductoRequestDTO(
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    String nombre,
    
    @Size(max = 255, message = "La descripción no debe exceder 255 caracteres")
    String descripcion,
    
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    BigDecimal precio,
    
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    Integer stock,
    
    @NotNull(message = "El ID de categoría es obligatorio")
    Long categoriaId
) {}