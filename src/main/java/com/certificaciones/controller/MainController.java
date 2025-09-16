package com.certificaciones.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registrar";
    }

    @GetMapping("/consultar")
    public String consultar() {
        return "consultar";
    }
}