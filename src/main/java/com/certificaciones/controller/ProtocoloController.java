package com.certificaciones.controller;

import com.certificaciones.model.ProtocoloPrueba;
import com.certificaciones.model.TipoElemento;
import com.certificaciones.repository.ProtocoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/protocolos")
public class ProtocoloController {

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("protocolo", new ProtocoloPrueba());
        model.addAttribute("tiposElemento", TipoElemento.values());
        return "protocolos";
    }

    @PostMapping("/guardar")
    public String guardarProtocolo(@ModelAttribute ProtocoloPrueba protocolo, RedirectAttributes redirectAttributes) {
        try {
            protocoloRepository.save(protocolo);
            redirectAttributes.addFlashAttribute("mensaje", "Protocolo de prueba guardado exitosamente");
            return "redirect:/protocolos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el protocolo de prueba");
            return "redirect:/protocolos";
        }
    }

    @GetMapping("/listar")
    public String listarProtocolos(Model model) {
        List<ProtocoloPrueba> protocolos = protocoloRepository.findAll();
        model.addAttribute("protocolos", protocolos);
        return "listar-protocolos";
    }

    @GetMapping("/editar/{id}")
    public String editarProtocolo(@PathVariable Long id, Model model) {
        Optional<ProtocoloPrueba> protocolo = protocoloRepository.findById(id);
        if (protocolo.isPresent()) {
            model.addAttribute("protocolo", protocolo.get());
            model.addAttribute("tiposElemento", TipoElemento.values());
            return "protocolos";
        }
        return "redirect:/protocolos/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProtocolo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            protocoloRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Protocolo de prueba eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el protocolo de prueba");
        }
        return "redirect:/protocolos/listar";
    }
}