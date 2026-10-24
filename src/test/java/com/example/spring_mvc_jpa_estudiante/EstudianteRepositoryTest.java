package com.example.spring_mvc_jpa_estudiante;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.example.spring_mvc_jpa_estudiante.model.Estudiante;
import com.example.spring_mvc_jpa_estudiante.repository.EstudianteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("h2") // fuerza el uso de H2 en memoria
class EstudianteRepositoryTest {

    @Autowired
    EstudianteRepository repository;

    @Test
    void guardar_y_encontrar_por_id() {
        Estudiante e = new Estudiante(100L, "Pablo");
        Estudiante saved = repository.save(e);

        Optional<Estudiante> found = repository.findById(100L);
        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("Pablo");
    }

    @Test
    void actualizar_nombre() {
        repository.save(new Estudiante(101L, "Ana"));
        Estudiante e = repository.findById(101L).orElseThrow();
        e.setNombre("Ana María");
        repository.save(e);

        Estudiante updated = repository.findById(101L).orElseThrow();
        assertThat(updated.getNombre()).isEqualTo("Ana María");
    }

    @Test
    void eliminar_por_id() {
        repository.save(new Estudiante(102L, "Luis"));
        repository.deleteById(102L);
        assertThat(repository.findById(102L)).isEmpty();
    }
}