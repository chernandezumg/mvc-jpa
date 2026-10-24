package com.example.spring_mvc_jpa_estudiante;

import com.example.spring_mvc_jpa_estudiante.model.Estudiante;
import com.example.spring_mvc_jpa_estudiante.repository.EstudianteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
class EstudianteMvcITest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EstudianteRepository repository;

    @Test
    void listar_estudiantes_devuelve_vista_y_modelo() throws Exception {
        repository.save(new Estudiante(1L, "Alice"));
        repository.save(new Estudiante(2L, "Bob"));


        mockMvc.perform(get("/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(view().name("estudiantes/lista"))
                .andExpect(model().attributeExists("estudiantes"))
                .andExpect(content().string(containsString("<td>1</td>")))
                .andExpect(content().string(containsString("<td>Alice</td>")))
                .andExpect(content().string(containsString("<td>2</td>")))
                .andExpect(content().string(containsString("<td>Bob</td>")))
                .andDo(print());
    }
}
