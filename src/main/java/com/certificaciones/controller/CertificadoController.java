package com.certificaciones.controller;

import com.certificaciones.model.CertificadoOperatividad;
import com.certificaciones.repository.CertificadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/certificados")
public class CertificadoController {

    @Autowired
    private CertificadoRepository certificadoRepository;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("certificado", new CertificadoOperatividad());
        return "certificados";
    }

    @PostMapping("/guardar")
    public String guardarCertificado(@ModelAttribute CertificadoOperatividad certificado, RedirectAttributes redirectAttributes) {
        try {
            certificadoRepository.save(certificado);
            redirectAttributes.addFlashAttribute("mensaje", "Certificado de operatividad guardado exitosamente");
            return "redirect:/certificados";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el certificado de operatividad");
            return "redirect:/certificados";
        }
    }

    @GetMapping("/listar")
    public String listarCertificados(Model model) {
        List<CertificadoOperatividad> certificados = certificadoRepository.findAll();
        model.addAttribute("certificados", certificados);
        return "listar-certificados";
    }

    @GetMapping("/editar/{id}")
    public String editarCertificado(@PathVariable Long id, Model model) {
        Optional<CertificadoOperatividad> certificado = certificadoRepository.findById(id);
        if (certificado.isPresent()) {
            model.addAttribute("certificado", certificado.get());
            return "certificados";
        }
        return "redirect:/certificados/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCertificado(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            certificadoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Certificado de operatividad eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el certificado de operatividad");
        }
        return "redirect:/certificados/listar";
    }
}