package org.examen.alquiler.controllers;

import org.examen.alquiler.models.Usuario;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {
    @RequestMapping("/usuarios")
    public List<Usuario> getUsuarios() {

        Usuario usuario = new Usuario();
        usuario.setUsuario("admin");
        usuario.setPassword("admin");
        usuario.setEmail("admin@gmail.com");
        usuario.setRol("ADMIN");
        usuario.setNombre("ronny");

        Usuario usuario2 = new Usuario();
        usuario2.setUsuario("admin2");
        usuario2.setPassword("admin2");
        usuario2.setEmail("admin2@gmail.com");
        usuario2.setRol("ADMIN");
        usuario2.setNombre("ronny2");

        Usuario usuario3 = new Usuario();
        usuario3.setUsuario("admin3");
        usuario3.setPassword("admin3");
        usuario3.setEmail("admin3@gmail.com");
        usuario3.setRol("ADMIN");
        usuario3.setNombre("ronny3");

        Usuario usuario4 = new Usuario();
        usuario4.setUsuario("admin4");
        usuario4.setPassword("admin4");
        usuario4.setEmail("admin4@gmail.com");
        usuario4.setRol("ADMIN");
        usuario4.setNombre("ronny4");

        return List.of(usuario, usuario2, usuario3, usuario4);
    }

}

