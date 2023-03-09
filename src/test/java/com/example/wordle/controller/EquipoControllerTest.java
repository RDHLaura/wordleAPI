package com.example.wordle.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class EquipoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllEquipo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/equipo"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetOneEquipo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/equipo/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/equipo/9999999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
