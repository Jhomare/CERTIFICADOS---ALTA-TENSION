package com.certificaciones.controller;

import com.certificaciones.model.ExamenMedico;
import com.certificaciones.repository.ExamenMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/examenes")
public class ExamenMedicoController {

    @Autowired
    private ExamenMedicoRepository examenMedicoRepository;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("examen", new ExamenMedico());
        return "examen-medico";
    }

    @PostMapping("/guardar")
    public String guardarExamen(@ModelAttribute ExamenMedico examen, RedirectAttributes redirectAttributes) {
        try {
            examenMedicoRepository.save(examen);
            redirectAttributes.addFlashAttribute("mensaje", "Examen médico guardado exitosamente");
            return "redirect:/examenes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el examen médico");
            return "redirect:/examenes";
        }
    }

    @GetMapping("/listar")
    public String listarExamenes(Model model) {
        List<ExamenMedico> examenes = examenMedicoRepository.findAll();
        model.addAttribute("examenes", examenes);
        return "listar-examenes";
    }

    @GetMapping("/editar/{id}")
    public String editarExamen(@PathVariable Long id, Model model) {
        Optional<ExamenMedico> examen = examenMedicoRepository.findById(id);
        if (examen.isPresent()) {
            model.addAttribute("examen", examen.get());
            return "examen-medico";
        }
        return "redirect:/examenes/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarExamen(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            examenMedicoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Examen médico eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el examen médico");
        }
        return "redirect:/examenes/listar";
    }
}