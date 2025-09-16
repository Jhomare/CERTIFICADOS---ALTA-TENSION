package com.certificaciones.controller;

import com.certificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/probar")
    public String ejecutarVerificacion() {
        notificacionService.verificarVencimientos();
        return "Verificación de vencimientos ejecutada manualmente.";
    }
}
