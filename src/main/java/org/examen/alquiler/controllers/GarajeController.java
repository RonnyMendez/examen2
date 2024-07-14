package org.examen.alquiler.controllers;

import org.examen.alquiler.models.Garaje;
import org.examen.alquiler.services.GarajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/garajes")
public class GarajeController {

    @Autowired
    private final GarajeService garajeService;

    public GarajeController(GarajeService garajeService) {
        this.garajeService = garajeService;
    }

    @GetMapping
    public List<Garaje> obtenerGarajes() {
        return garajeService.getGarajes();
    }

    @GetMapping("/{garajeId}")
    public Optional<Garaje> obtenerGarajePorId(@PathVariable("garajeId") Long garajeId) {
        return garajeService.getGarajePorId(garajeId);
    }

    @PostMapping
    public void guardarGaraje(@RequestBody Garaje garaje) {
        garajeService.guardarGaraje(garaje);
    }

    @DeleteMapping("/delete/{garajeId}")
    public void deleteGaraje(@PathVariable("garajeId") Long id) {
        garajeService.deleteGaraje(id);
    }

    @PutMapping("/{garajeId}")
    public ResponseEntity<Void> actualizarGaraje(@PathVariable("garajeId") Long garajeId, @RequestBody Garaje garaje) {
        Optional<Garaje> existingGaraje = garajeService.getGarajePorId(garajeId);
        if (existingGaraje.isPresent()) {
            existingGaraje.get().setNombre(garaje.getNombre());
            existingGaraje.get().setDireccion(garaje.getDireccion());
            garajeService.guardarGaraje(existingGaraje.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
