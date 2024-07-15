package org.examen.alquiler.controllers;

import org.examen.alquiler.models.Automovil;
import org.examen.alquiler.services.AutomovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/automoviles")
public class AutomovilController {

    @Autowired
    private final AutomovilService automovilService;

    public AutomovilController(AutomovilService automovilService) {
        this.automovilService = automovilService;
    }

    @GetMapping
    public List<Automovil> obtenerAutomoviles() {
        return automovilService.getAutomoviles();
    }

    @GetMapping("/{automovilId}")
    public Optional<Automovil> obtenerAutomovilPorId(@PathVariable("automovilId") Long automovilId) {
        return automovilService.getAutomovilPorId(automovilId);
    }

    @PostMapping
    public ResponseEntity<Void> guardarAutomovil(@RequestBody Automovil automovil) {
        automovilService.guardarAutomovil(automovil);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{automovilId}")
    public ResponseEntity<Void> deleteAutomovil(@PathVariable("automovilId") Long automovilId) {
        automovilService.deleteAutomovil(automovilId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{automovilId}")
    public ResponseEntity<Void> actualizarAutomovil(@PathVariable("automovilId") Long automovilId, @RequestBody Automovil automovil) {
        Optional<Automovil> existingAutomovil = automovilService.getAutomovilPorId(automovilId);
        if (existingAutomovil.isPresent()) {
            // Actualizar los campos que se pueden modificar
            Automovil updatedAutomovil = existingAutomovil.get();
            updatedAutomovil.setMatricula(automovil.getMatricula());
            updatedAutomovil.setModelo(automovil.getModelo());
            updatedAutomovil.setColor(automovil.getColor());
            updatedAutomovil.setMarca(automovil.getMarca());
            updatedAutomovil.setGaraje(automovil.getGaraje()); // Asegúrate de manejar correctamente la relación con Garaje

            automovilService.guardarAutomovil(updatedAutomovil);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}