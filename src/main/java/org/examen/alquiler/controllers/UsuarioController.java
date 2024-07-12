package org.examen.alquiler.controllers;

import org.examen.alquiler.models.Usuario;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @RequestMapping("/usuarios")
    public Usuario getUsuarios() {
        Usuario usuario = new Usuario();
        usuario.setUsuario("admin");
        usuario.setPassword("admin");
        usuario.setEmail("admin@gmail.com");
        usuario.setRol("ADMIN");
        usuario.setNombre("ronny");
        return usuario;
    }
}

