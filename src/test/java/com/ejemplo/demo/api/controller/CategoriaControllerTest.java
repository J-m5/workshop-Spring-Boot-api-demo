package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.CategoriaRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearCategoriaDeberiaRetornar201() throws Exception {
        CategoriaRequestDTO request = new CategoriaRequestDTO("Electrónicos", "Productos electrónicos");

        mockMvc.perform(post("/api/v1/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void crearCategoriaConNombreVacioDeberiaRetornar400() throws Exception {
        CategoriaRequestDTO request = new CategoriaRequestDTO("", "Descripción");

        mockMvc.perform(post("/api/v1/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void obtenerCategoriaInexistenteDeberiaRetornar404() throws Exception {
        mockMvc.perform(get("/api/v1/categorias/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.codigo").value("RESOURCE_NOT_FOUND"));
    }
}