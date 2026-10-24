package com.example.spring_mvc_jpa_estudiante.repository;


import com.example.spring_mvc_jpa_estudiante.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> { }