package org.examen.alquiler.controllers.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {
    @GetMapping("/listarUsuarios")
    public String listarUsuarios() {
        return "/usuarios/listarUsuarios";
    }

    @GetMapping("/crearUsuario")
    public String crearUsuario() {
        return "/usuarios/crearUsuario";
    }

    @GetMapping("/listarClientes")
    public String listarClientes() {
        return "/clientes/listarClientes";
    }

    @GetMapping("/crearCliente")
    public String crearCliente() {
        return "/clientes/crearCliente";
    }

    @GetMapping("/listarGarajes")
    public String listarGarajes() {
        return "/garajes/listarGarajes";
    }

    @GetMapping("/crearGaraje")
    public String crearGaraje() {
        return "/garajes/crearGaraje";
    }

    @GetMapping("/listarAutomoviles")
    public String listarAutomoviles() {
        return "/automoviles/listarAutomoviles";
    }

    @GetMapping("/crearAutomovil")
    public String crearAutomovil() {
        return "/automoviles/crearAutomovil";
    }

    @GetMapping("/listarReservas")
    public String listarReservas() {
        return "/reservas/listarReservas";
    }

    @GetMapping("/crearReserva")
    public String crearReserva() {
        return "/reservas/crearReserva";
    }
}
