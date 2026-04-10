package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.SaludoResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SaludoService {

    public SaludoResponse crearSaludo(String nombre) {
        String nombreNormalizado = normalizarNombre(nombre);
        String mensaje = "Hola, %s. Bienvenido a Spring Boot 3!".formatted(nombreNormalizado);
        return new SaludoResponse(mensaje, Instant.now());
    }

    String normalizarNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
        
        String nombreLimpio = nombre.trim();
        
        if (nombreLimpio.isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        
        if (nombreLimpio.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre no puede contener números");
        }
        
        String primeraLetra = nombreLimpio.substring(0, 1).toUpperCase();
        String resto = nombreLimpio.substring(1).toLowerCase();
        
        return primeraLetra + resto;
    }
   
    }
