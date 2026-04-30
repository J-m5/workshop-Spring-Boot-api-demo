package com.ejemplo.demo.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    String nombre,
    
    @Size(max = 255, message = "La descripción no debe exceder 255 caracteres")
    String descripcion
) {}
