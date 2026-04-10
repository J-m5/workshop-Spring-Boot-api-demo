package com.ejemplo.demo.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PrestamoRequest(
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    BigDecimal monto,
    
    @NotNull(message = "La tasa anual es obligatoria")
    @DecimalMin(value = "0.01", message = "La tasa anual debe ser mayor a 0")
    BigDecimal tasaAnual,
    
    @NotNull(message = "El número de meses es obligatorio")
    @Min(value = 1, message = "Los meses deben ser al menos 1")
    int meses
) {}
