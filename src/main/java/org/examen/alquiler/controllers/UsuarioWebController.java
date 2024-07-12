package org.examen.alquiler.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsuarioWebController {
    @GetMapping("/listarUsuarios")
    public String listarUsuarios() {
        return "/usuarios/listarUsuarios";
    }
}
