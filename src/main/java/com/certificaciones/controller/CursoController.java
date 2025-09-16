package com.certificaciones.controller;

import com.certificaciones.model.Curso;
import com.certificaciones.model.TipoCurso;
import com.certificaciones.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("tiposCurso", TipoCurso.values());
        return "cursos";
    }

    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso, RedirectAttributes redirectAttributes) {
        try {
            cursoRepository.save(curso);
            redirectAttributes.addFlashAttribute("mensaje", "Curso guardado exitosamente");
            return "redirect:/cursos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el curso");
            return "redirect:/cursos";
        }
    }

    @GetMapping("/listar")
    public String listarCursos(Model model) {
        List<Curso> cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "listar-cursos";
    }

    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable Long id, Model model) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            model.addAttribute("curso", curso.get());
            model.addAttribute("tiposCurso", TipoCurso.values());
            return "cursos";
        }
        return "redirect:/cursos/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            cursoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Curso eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el curso");
        }
        return "redirect:/cursos/listar";
    }
}