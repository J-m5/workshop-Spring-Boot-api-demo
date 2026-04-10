package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/simulaciones")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/prestamo")
    public ResponseEntity<PrestamoResponse> simularPrestamo(@Valid @RequestBody PrestamoRequest request) {
        BigDecimal cuotaMensual = prestamoService.calcularCuotaMensual(
            request.monto(), 
            request.tasaAnual(), 
            request.meses()
        );
        
        BigDecimal totalPagar = cuotaMensual.multiply(BigDecimal.valueOf(request.meses()));
        BigDecimal interesTotal = totalPagar.subtract(request.monto());
        
        PrestamoResponse response = new PrestamoResponse(cuotaMensual, interesTotal, totalPagar);
        return ResponseEntity.ok(response);
    }
}