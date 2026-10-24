package com.example.spring_mvc_jpa_estudiante.controller;

import com.example.spring_mvc_jpa_estudiante.model.Estudiante;
import com.example.spring_mvc_jpa_estudiante.repository.EstudianteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteRepository repository;

    public EstudianteController(EstudianteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("estudiantes", repository.findAll());
        return "estudiantes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Estudiante estudiante, BindingResult br, Model model) {
        if (repository.existsById(estudiante.getId())) {
            br.rejectValue("id", "duplicate", "Ya existe un estudiante con ese ID");
            return "estudiantes/form";
        }
        repository.save(estudiante);
        return "redirect:/estudiantes";
    }
}
