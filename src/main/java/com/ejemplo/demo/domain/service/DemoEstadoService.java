package com.ejemplo.demo.domain.service;

import org.springframework.stereotype.Service;

@Service
public class DemoEstadoService {

    private String singletonValor = "inicial";
    private String manualValor = "inicial";

    public String obtenerSingleton() {
        return singletonValor;
    }
    
    public String actualizarSingleton(String valor) {
        this.singletonValor = valor;
        return singletonValor;
    }
    
    public String resetSingleton() {
        this.singletonValor = "inicial";
        return singletonValor;
    }
    
    public String obtenerManual() {
        return manualValor;
    }
    
    public String actualizarManual(String valor) {
        this.manualValor = valor;
        return manualValor;
    }
}
