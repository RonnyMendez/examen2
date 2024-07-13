package org.examen.alquiler.controllers;

import org.examen.alquiler.models.Usuario;
import org.examen.alquiler.services.UsuarioService; // Importa el service UsuarioService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService; // Inyecta UsuarioService

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.getUsuarios(); // Utiliza el método del service
    }

    @GetMapping("/{usuarioId}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.getUsuarioPorId(usuarioId); // Utiliza el método del service
    }
    @PostMapping
    public void guardarUsuario(@RequestBody Usuario usuario) {
        usuarioService.guardarUsuario(usuario); // Utiliza el método del service
    }

    @DeleteMapping("/delete/{usuarioId}")
    public void deleteUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId); // Utiliza el método del service
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Void> actualizarUsuario(@PathVariable("usuarioId") Long usuarioId, @RequestBody Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioService.getUsuarioPorId(usuarioId);
        if (existingUsuario.isPresent()) {
            // Actualizar los campos que se pueden modificar
            existingUsuario.get().setNombre(usuario.getNombre());
            existingUsuario.get().setUsuario(usuario.getUsuario());
            existingUsuario.get().setEmail(usuario.getEmail());
            existingUsuario.get().setRol(usuario.getRol());

            // Si se proporcionó una nueva contraseña, actualizarla
            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                existingUsuario.get().setPassword(usuario.getPassword());
            }

            usuarioService.guardarUsuario(existingUsuario.get()); // Guardar el usuario actualizado
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



