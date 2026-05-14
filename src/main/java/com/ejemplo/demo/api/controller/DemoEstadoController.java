package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.DemoEstadoResponseDTO;
import com.ejemplo.demo.domain.service.DemoEstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo/estado")
public class DemoEstadoController {

    private final DemoEstadoService demoEstadoService;

    public DemoEstadoController(DemoEstadoService demoEstadoService) {
        this.demoEstadoService = demoEstadoService;
    }

    @GetMapping("/singleton")
    public ResponseEntity<DemoEstadoResponseDTO> obtenerSingleton() {
        return ResponseEntity.ok(new DemoEstadoResponseDTO("singleton", demoEstadoService.obtenerSingleton()));
    }

    @PostMapping("/singleton/{valor}")
    public ResponseEntity<DemoEstadoResponseDTO> actualizarSingleton(@PathVariable String valor) {
        return ResponseEntity.ok(new DemoEstadoResponseDTO("singleton", demoEstadoService.actualizarSingleton(valor)));
    }

    @PostMapping("/singleton/reset")
    public ResponseEntity<DemoEstadoResponseDTO> reiniciarSingleton() {
        return ResponseEntity.ok(new DemoEstadoResponseDTO("singleton", demoEstadoService.resetSingleton()));
    }

    @GetMapping("/manual")
    public ResponseEntity<DemoEstadoResponseDTO> obtenerManual() {
        return ResponseEntity.ok(new DemoEstadoResponseDTO("manual", demoEstadoService.obtenerManual()));
    }

    @PostMapping("/manual/{valor}")
    public ResponseEntity<DemoEstadoResponseDTO> actualizarManual(@PathVariable String valor) {
        return ResponseEntity.ok(new DemoEstadoResponseDTO("manual", demoEstadoService.actualizarManual(valor)));
    }
}