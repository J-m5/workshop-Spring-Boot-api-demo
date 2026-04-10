package com.ejemplo.demo.domain.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PrestamoService {

    public BigDecimal calcularCuotaMensual(BigDecimal monto, BigDecimal tasaAnual, int meses) {
        BigDecimal tasaMensual = tasaAnual
            .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)
            .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        
        BigDecimal unoMasTasa = BigDecimal.ONE.add(tasaMensual);
        BigDecimal potencia = unoMasTasa.pow(meses);
        
        BigDecimal numerador = tasaMensual.multiply(potencia);
        BigDecimal denominador = potencia.subtract(BigDecimal.ONE);
        BigDecimal factor = numerador.divide(denominador, 10, RoundingMode.HALF_UP);
        
        return monto.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }
}
