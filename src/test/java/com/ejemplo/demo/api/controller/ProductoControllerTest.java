package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.ProductoRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearProductoDeberiaRetornar201() throws Exception {
        ProductoRequestDTO request = new ProductoRequestDTO(
            "Teclado", "Teclado mecánico", new BigDecimal("50000"), 10, 1L
        );

        mockMvc.perform(post("/api/v1/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void crearProductoConNombreVacioDeberiaRetornar400() throws Exception {
        ProductoRequestDTO request = new ProductoRequestDTO(
            "", "Teclado mecánico", new BigDecimal("50000"), 10, 1L
        );

        mockMvc.perform(post("/api/v1/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void obtenerProductoInexistenteDeberiaRetornar404() throws Exception {
        mockMvc.perform(get("/api/v1/productos/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.codigo").value("RESOURCE_NOT_FOUND"));
    }
}
